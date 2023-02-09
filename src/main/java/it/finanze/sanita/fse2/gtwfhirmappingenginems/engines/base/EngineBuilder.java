package it.finanze.sanita.fse2.gtwfhirmappingenginems.engines.base;

import ch.ahdis.matchbox.engine.CdaMappingEngine;
import it.finanze.sanita.fse2.gtwfhirmappingenginems.enums.FhirTypeEnum;
import it.finanze.sanita.fse2.gtwfhirmappingenginems.exception.OperationException;
import it.finanze.sanita.fse2.gtwfhirmappingenginems.exception.engine.EngineBuilderException;
import it.finanze.sanita.fse2.gtwfhirmappingenginems.repository.IEngineRepo;
import it.finanze.sanita.fse2.gtwfhirmappingenginems.repository.ITransformRepo;
import it.finanze.sanita.fse2.gtwfhirmappingenginems.repository.entity.TransformETY;
import it.finanze.sanita.fse2.gtwfhirmappingenginems.repository.entity.engine.EngineETY;
import it.finanze.sanita.fse2.gtwfhirmappingenginems.repository.entity.engine.sub.EngineMap;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.hl7.fhir.r4.formats.JsonParser;
import org.hl7.fhir.r4.model.StructureDefinition;
import org.hl7.fhir.r4.model.StructureMap;
import org.hl7.fhir.r4.model.ValueSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static ch.ahdis.matchbox.engine.CdaMappingEngine.CdaMappingEngineBuilder;
import static it.finanze.sanita.fse2.gtwfhirmappingenginems.config.Constants.Logs.*;

@Slf4j
@Component
public class EngineBuilder {

    private static final String JSON_EXT = ".json";

    @Autowired
    private ITransformRepo transform;

    @Autowired
    private IEngineRepo engine;

    public Engine fromId(String id) throws OperationException, EngineBuilderException {
        // Retrieve entity
        EngineETY engine = this.engine.findById(id);
        // Check nullity
        if (engine == null) throw new EngineBuilderException(ERR_BLD_FIND_BY_ID_ENGINE);
        // Create mapping for all available entities
        Map<String, TransformETY> entities = createEntitiesMap(engine);
        // Create root mapping
        Map<String, String> roots = createRootsMap(engine.getRoots(), entities);
        // Create engine
        CdaMappingEngine instance = createEngine();
        // Register resources
        registerItems(instance, entities);
        // Return newly fresh engine
        return new Engine(id, roots, instance);
    }

    private Map<String, TransformETY> createEntitiesMap(EngineETY e) throws OperationException, EngineBuilderException {
        HashMap<String, TransformETY> out = new HashMap<>();
        // Iterate and populate
        for (ObjectId id : e.getFiles()) {
            // Get id
            String hex = id.toHexString();
            // Retrieve entity
            TransformETY e0 = transform.findById(hex);
            // Check nullity
            if (e0 == null) throw new EngineBuilderException(ERR_BLD_FIND_BY_ID_FHIR);
            // Store
            out.putIfAbsent(hex, e0);
        }
        return out;
    }

    private Map<String, String> createRootsMap(List<EngineMap> e, Map<String, TransformETY> entities) throws EngineBuilderException {
        Map<String, String> roots = new HashMap<>();
        for (EngineMap map : e) {
            // Get object identifier
            String id = map.getOid().toHexString();
            // Save in map
            roots.putIfAbsent(id, String.format("%s|%s", map.getUri(), map.getVersion()));
            // Consistency check
            if(!entities.containsKey(id)) {
                throw new EngineBuilderException(
                    String.format(ERR_BLD_ROOT_UNAVAILALE, map.getUri(), id)
                );
            }
        }
        return roots;
    }

    private CdaMappingEngine createEngine() throws EngineBuilderException {
        CdaMappingEngine engine;
        try {
            // Preload default packages
            engine = new CdaMappingEngineBuilder().getEngine();
        } catch (IOException | URISyntaxException e) {
            throw new EngineBuilderException(ERR_BLD_ENGINE_UNAVAILALE, e);
        }
        return engine;
    }

    private void registerItems(CdaMappingEngine engine, Map<String, TransformETY> entities) throws EngineBuilderException {
        for (Entry<String, TransformETY> i : entities.entrySet()) {
            // Retrieve instance
            TransformETY value = i.getValue();
            // Retrieve type
            FhirTypeEnum type = value.getType();
            // Register entity
            switch (type) {
                case Map:
                    registerMap(engine, value);
                    break;
                case Definition:
                    registerDefinition(engine, value);
                    break;
                case Valueset:
                    registerValueset(engine, value);
                    break;
                default:
                    throw new EngineBuilderException(
                        String.format(ERR_BLD_UKNOWN_TYPE, value.getUri(), type)
                    );
            }
        }
    }

    private void registerMap(CdaMappingEngine engine, TransformETY e) throws EngineBuilderException {
        try {
            JsonParser parser = new JsonParser();
            // Retrieve data
            String data = new String(e.getContent().getData());
            // Parse map
            StructureMap map;
            if(e.getFilename().endsWith(JSON_EXT)) {
                map = (StructureMap) parser.parse(data);
            } else {
                map = engine.parseMap(data);
            }
            // Set URI and version
            map.setUrl(e.getUri());
            map.setVersion(e.getVersion());
            // Add to engine
            engine.addCanonicalResource(map);
        } catch (Exception ex) {
            throw new EngineBuilderException(
                String.format(ERR_BLD_REGISTER_FHIR, e.getUri(), FhirTypeEnum.Map.getName()),
                ex
            );
        }
    }

    private void registerDefinition(CdaMappingEngine engine, TransformETY e) throws EngineBuilderException {
        try {
            JsonParser parser = new JsonParser();
            // Retrieve data
            String data = new String(e.getContent().getData());
            // Parse map
            StructureDefinition definition = (StructureDefinition) parser.parse(data);
            // Set URI and version
            definition.setUrl(e.getUri());
            definition.setVersion(e.getVersion());
            // Add to engine
            engine.addCanonicalResource(definition);
        } catch (Exception ex) {
            throw new EngineBuilderException(
                String.format(ERR_BLD_REGISTER_FHIR, e.getUri(), FhirTypeEnum.Definition.getName()),
                ex
            );
        }
    }

    private void registerValueset(CdaMappingEngine engine, TransformETY e) throws EngineBuilderException {
        try {
            JsonParser parser = new JsonParser();
            // Retrieve data
            String data = new String(e.getContent().getData());
            // Parse map
            ValueSet valueset = (ValueSet) parser.parse(data);
            // Set URI and version
            valueset.setUrl(e.getUri());
            valueset.setVersion(e.getVersion());
            // Add to engine
            engine.addCanonicalResource(valueset);
        } catch (Exception ex) {
            throw new EngineBuilderException(
                String.format(ERR_BLD_REGISTER_FHIR, e.getUri(), FhirTypeEnum.Valueset.getName()),
                ex
            );
        }
    }

}

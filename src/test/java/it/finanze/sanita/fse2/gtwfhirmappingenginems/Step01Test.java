package it.finanze.sanita.fse2.gtwfhirmappingenginems;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.Binary;
import org.hl7.fhir.r5.model.StructureMap;
import org.hl7.fhir.r5.utils.structuremap.StructureMapUtilities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;

import it.finanze.sanita.fse2.gtwfhirmappingenginems.config.Constants;
import it.finanze.sanita.fse2.gtwfhirmappingenginems.engine.Trasformer;
import it.finanze.sanita.fse2.gtwfhirmappingenginems.helper.ContextHelper;
import it.finanze.sanita.fse2.gtwfhirmappingenginems.service.impl.StructureDefinitionSRV;
import it.finanze.sanita.fse2.gtwfhirmappingenginems.utility.FileUtility;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(Constants.Profile.TEST)
class Step01Test extends AbstractTest {
	 
	@Autowired
	private StructureDefinitionSRV structureDefinitionSRV;



	@BeforeEach
    void setup() {
        mongoTemplate.dropCollection("transform");
    }
     
	 @Test
	 void testStep01() throws Exception {
		 Document doc = new Document();
		 Document struttura1 = createFakeDefinitionLeftDoc(loadStructurefromPath("step01", "structuredefinition-tleft-snap.json"));
		 Document struttura2 = createFakeDefinitionRightDoc(loadStructurefromPath("step01", "structuredefinition-tright-snap.json"));
		 Document mappa = createFakeMapDoc(loadMapFromPath("step01", "step01.map"));
 
		 List<Document> structureToSave = new ArrayList<>();
		 structureToSave.add(struttura1);
		 structureToSave.add(struttura2);
		 List<Document> mapsToSave = new ArrayList<>();
		 mapsToSave.add(mappa);
		 doc.put("maps", mapsToSave);
		 doc.put("definitions", structureToSave);
		 doc.put("version", "1.0");
		 doc.put("deleted", false);
		 
		 mongoTemplate.insert(doc, "transform");
		 structureDefinitionSRV.postConstruct();
		 
		 InputStream xmlStep01 = new ByteArrayInputStream(FileUtility.getFileFromInternalResources("step01" + File.separator + "source" + File.separator + "source.xml"));
		 StructureMapUtilities smu5 = new StructureMapUtilities(ContextHelper.getSimpleWorkerContextR5());
		 StructureMap structuredMap = smu5.parse(new String(FileUtility.getFileFromInternalResources("step01" + File.separator + "map" + File.separator + "step01.map")), "map");
		 String json01 = Trasformer.transform(xmlStep01, structuredMap);
		 System.out.println(json01);
		 assertNotNull(json01);
	 }
	
 
}

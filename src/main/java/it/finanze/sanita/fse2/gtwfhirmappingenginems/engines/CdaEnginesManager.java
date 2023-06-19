/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package it.finanze.sanita.fse2.gtwfhirmappingenginems.engines;

import it.finanze.sanita.fse2.gtwfhirmappingenginems.engines.base.Engine;
import it.finanze.sanita.fse2.gtwfhirmappingenginems.engines.base.EngineBuilder;
import it.finanze.sanita.fse2.gtwfhirmappingenginems.engines.data.RootData;
import it.finanze.sanita.fse2.gtwfhirmappingenginems.exception.OperationException;
import it.finanze.sanita.fse2.gtwfhirmappingenginems.exception.engine.EngineBuilderException;
import it.finanze.sanita.fse2.gtwfhirmappingenginems.exception.engine.EngineException;
import it.finanze.sanita.fse2.gtwfhirmappingenginems.exception.engine.EngineInitException;
import it.finanze.sanita.fse2.gtwfhirmappingenginems.repository.IEngineRepo;
import it.finanze.sanita.fse2.gtwfhirmappingenginems.repository.entity.engine.EngineETY;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.hl7.fhir.r4.model.Bundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static it.finanze.sanita.fse2.gtwfhirmappingenginems.config.Constants.Logs.*;
import static it.finanze.sanita.fse2.gtwfhirmappingenginems.config.EngineCFG.ENGINE_EXECUTOR;

@Slf4j
@Component
public class CdaEnginesManager {

    private final IEngineRepo repository;
    private final EngineBuilder builder;
    private final ConcurrentHashMap<String, Engine> engines;
    private volatile boolean ready;
    private volatile boolean running;

    public CdaEnginesManager(
        @Autowired IEngineRepo repository,
        @Autowired EngineBuilder builder
    ) {
        this.repository = repository;
        this.builder = builder;
        this.engines = new ConcurrentHashMap<>();
        this.ready = false;
    }

    @Scheduled(cron = "${engine.scheduler.invoke}")
    @SchedulerLock(name = "invokeGTWEngineScheduler")
    @Async(ENGINE_EXECUTOR)
    public void refresh() {
        update();
    }

    /**
     * <p><b>DO NOT USE</b> this method for any other purpose than testing.</p>
     * Use the asynchronous version of this method for production
     * @see CdaEnginesManager#refresh()
     */
    public void refreshSync() {
        update();
    }

    /**
     * <p><b>DO NOT USE</b> this method for any other purpose than testing.</p>
     * This method clear the engines memory and reset the ready flag
     */
    public void reset() {
        this.ready = false;
        engines.clear();
    }

    private void update() {
        // Set running flag
        running = true;
        log.info("Beginning engine refreshing process");
        List<EngineETY> lists = lists();
        // Start un-registering process
        unregister(lists);
        // Start registering process
        register(lists);
        // Set flag (start-up only)
        if(!ready) ready = true;
        log.info("Finishing engine refreshing process");
        // Reset running flag
        running = false;
    }


    public Bundle transform(String cda, String engineId, String objectId) throws IOException {
        if(!ready) throw new EngineInitException(ERR_ENG_UNAVAILABLE);
        Engine obj = engines.get(engineId);
        if (obj == null) throw new EngineException(ERR_ENG_NULL);
        RootData root = obj.getRoots().get(objectId);
        if (root == null) throw new EngineException(ERR_ENG_ROOT_MAP);
        String uri = root.getUri();
        if (uri == null) throw new EngineException(ERR_ENG_ROOT_URI);
        return obj.getInstance().transformCdaToFhir(cda, uri);
    }

    public ConcurrentHashMap<String, Engine> engines() {
        return engines;
    }

    private List<EngineETY> lists() {
        List<EngineETY> list = new ArrayList<>();
        try {
            // Retrieve available engines
            list = repository.find();
        } catch (OperationException e) {
            log.error("Unable to retrieve all available engines", e);
        }
        return list;
    }
    private void register(List<EngineETY> list) {
        for (EngineETY e : list) {
            // Retrieve engine id
            String id = e.getId();
            // Check if instance exists
            if(!engines.containsKey(id)) {
                // Spawn engine
                Optional<Engine> engine = create(id);
                // Consistency check
                if(engine.isPresent()) {
                    // Update
                    engines.put(id, engine.get());
                    // Mark as available, if unsuccessful unload from memory
                    if (!available(id)) {
                        log.debug("Removing engine {} because couldn't set as available", id);
                        engines.remove(id);
                    }
                }
            } else {
                log.debug("Skipping engine {} because it already exists in-memory", id);
            }
        }
    }

    private void unregister(List<EngineETY> list) {
        // Retrieve current engines id
        // [B]
        List<String> current = Collections.list(engines.keys());
        // [A,C,D]
        List<String> queue = list.stream().map(EngineETY::getId).collect(Collectors.toList());
        // We are <keeping> all missing engines from the collection
        current.removeAll(queue);
        // Unload engines
        for (String id : current) {
            log.debug("Removing engine {}", id);
            engines.remove(id);
        }
    }

    private Optional<Engine> create(String id) {
        Engine e = null;
        try {
            e = builder.fromId(id);
        } catch (OperationException | EngineBuilderException ex) {
            log.error("Unable to instance engine", ex);
            log.error("Skipping {} ...", id);
        }
        return Optional.ofNullable(e);
    }
    private boolean available(String id) {
        boolean b = false;
        try {
            b = repository.enable(id);
        } catch (OperationException e) {
            log.error("Unable to mark engine '{}' as enabled", id);
        }
        return b;
    }
    public boolean isRunning() {
        return running;
    }
}

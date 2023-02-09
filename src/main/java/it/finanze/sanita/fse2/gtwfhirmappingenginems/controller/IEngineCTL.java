package it.finanze.sanita.fse2.gtwfhirmappingenginems.controller;

import io.swagger.v3.oas.annotations.Operation;
import it.finanze.sanita.fse2.gtwfhirmappingenginems.dto.res.EngRefreshResDTO;
import it.finanze.sanita.fse2.gtwfhirmappingenginems.dto.res.EngStatusResDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static it.finanze.sanita.fse2.gtwfhirmappingenginems.utility.RouteUtility.*;

@RequestMapping(ENGINE_MAPPER)
public interface IEngineCTL {

    @GetMapping(
        value = ENGINE_STATUS_API,
        produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    @Operation(description = "Restituisce lo stato corrente degli engine disponibili")
    EngStatusResDTO status();

    @GetMapping(
        value = ENGINE_REFRESH_API,
        produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    @Operation(description = "Forza il refresh degli engine")
    EngRefreshResDTO run();

}

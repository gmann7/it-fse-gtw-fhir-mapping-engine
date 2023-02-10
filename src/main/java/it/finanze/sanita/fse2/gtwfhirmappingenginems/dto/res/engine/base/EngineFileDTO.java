package it.finanze.sanita.fse2.gtwfhirmappingenginems.dto.res.engine.base;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map.Entry;

@Getter
@AllArgsConstructor
public class EngineFileDTO {
    private String id;
    private String uri;

    public EngineFileDTO(Entry<String, String> pair) {
        this.id = pair.getKey();
        this.uri = pair.getValue();
    }

}

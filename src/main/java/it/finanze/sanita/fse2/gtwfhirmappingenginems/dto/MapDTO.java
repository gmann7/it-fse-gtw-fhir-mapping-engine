/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package it.finanze.sanita.fse2.gtwfhirmappingenginems.dto;

import java.util.Date;

import org.bson.types.Binary;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Structure to map structure map.
 */
@Data
@NoArgsConstructor
public class MapDTO {

	private Binary contentStructureMap;

	private String nameStructureMap;

	private String templateIdRoot;
	
	private String version;
	
	private Date lastUpdateDate;
}

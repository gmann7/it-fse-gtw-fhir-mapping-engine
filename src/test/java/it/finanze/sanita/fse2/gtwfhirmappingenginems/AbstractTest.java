package it.finanze.sanita.fse2.gtwfhirmappingenginems;

import java.io.File;

import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.client.RestTemplate;

import it.finanze.sanita.fse2.gtwfhirmappingenginems.exception.BusinessException;
import it.finanze.sanita.fse2.gtwfhirmappingenginems.repository.entity.StructureDefinitionETY;
import it.finanze.sanita.fse2.gtwfhirmappingenginems.repository.entity.StructureMapETY;
import it.finanze.sanita.fse2.gtwfhirmappingenginems.utility.FileUtility;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractTest {

	@Autowired
	private MongoTemplate mongoTemplate;

	protected void dropCollections() {
		mongoTemplate.dropCollection(StructureMapETY.class);
		mongoTemplate.dropCollection(StructureDefinitionETY.class);
	}
	
	protected void saveStructureDefinition(final String completePath, final String rootDefinition) {
		try {
			File folder = new File(completePath);
			File[] listOfFiles = folder.listFiles();
			
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					String fileName = listOfFiles[i].getName();
					byte[] content = FileUtility.getFileFromInternalResources(rootDefinition + File.separator + "structure" +File.separator +fileName);
					StructureDefinitionETY defETY = new StructureDefinitionETY();
					defETY.setContentFile(new Binary(content));
					defETY.setFileName(fileName);
					mongoTemplate.insert(defETY);
				}
			}
		} catch(Exception ex) {
			log.error("Errore mentre salvi le structure definition : " ,ex);
			throw new BusinessException("Errore mentre salvi le structure definition : " ,ex);
		}
	}
}

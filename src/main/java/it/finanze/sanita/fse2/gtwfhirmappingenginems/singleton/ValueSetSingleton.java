package it.finanze.sanita.fse2.gtwfhirmappingenginems.singleton;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.hl7.fhir.r4.model.ValueSet;

import it.finanze.sanita.fse2.gtwfhirmappingenginems.exception.BusinessException;
import it.finanze.sanita.fse2.gtwfhirmappingenginems.helper.FHIRR4Helper;
import it.finanze.sanita.fse2.gtwfhirmappingenginems.repository.IValueSetRepo;
import it.finanze.sanita.fse2.gtwfhirmappingenginems.repository.entity.ValuesetETY;
import it.finanze.sanita.fse2.gtwfhirmappingenginems.utility.StringUtility;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ValueSetSingleton {
	
	private static ValueSetSingleton instance;
    
    @Getter
    private static Map<String, ValueSetSingleton> mapInstance;
    
    @Getter
    private String valuesetName;
    
    @Getter
    private ValueSet valueSet;
    
    private static IValueSetRepo valuesetRepo;

	private ValueSetSingleton(String inValuesetName,ValueSet inValueSet) {
		valueSet = inValueSet;
		valuesetName = inValuesetName;
	}

	public static void initialize(final IValueSetRepo inValuesetRepo) {
		valuesetRepo = inValuesetRepo;
	}
 
    public static ValueSetSingleton getAndUpdateInstance(final String valuesetUri) {
    	String valuesetName = StringUtility.getNameFromUrl(valuesetUri);
    	if(mapInstance != null) {
			instance = mapInstance.get(valuesetName); 
		} else {
			mapInstance = new HashMap<>();
		}

    	synchronized(StructureMapSingleton.class) {
			if (instance == null) {
				try { 
	            	ValuesetETY valueSetETY = valuesetRepo.findValueSetByName(valuesetName);
	            	ValueSet valSet = FHIRR4Helper.deserializeResource(ValueSet.class, new String(valueSetETY.getContentValueset().getData(),StandardCharsets.UTF_8));
	                instance = new ValueSetSingleton(valuesetName, valSet);
	                mapInstance.put(valuesetName,instance);
				} catch(Exception ex) {
					log.error("Error while retrieving and structure :", ex);
					throw new BusinessException("Error while retrieving and structure :", ex);
				}
			}
		}
		return instance;
	}
   
}
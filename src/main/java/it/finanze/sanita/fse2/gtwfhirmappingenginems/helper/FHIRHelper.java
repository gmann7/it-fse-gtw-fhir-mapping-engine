package it.finanze.sanita.fse2.gtwfhirmappingenginems.helper;


import org.hl7.fhir.instance.model.api.IBaseResource;

import ca.uhn.fhir.parser.IParser;

public class FHIRHelper {

	
	private FHIRHelper() {
		//This method is left intentionally empty.
	}
	
	public static <T> T deserializeResource(Class<? extends IBaseResource> resourceClass, String input) {
		IParser parser = ContextHelper.getFhirContextR5().newJsonParser();
		return (T) parser.parseResource(resourceClass, input);
	}
	
	public static <T> T deserializeXMLResource(Class<? extends IBaseResource> resourceClass, String input) {
		IParser parser = ContextHelper.getFhirContextR5().newJsonParser();
		return (T) parser.parseResource(resourceClass, input);
	}
	
}

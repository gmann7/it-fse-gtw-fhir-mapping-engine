/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package it.finanze.sanita.fse2.gtwfhirmappingenginems.enums;

import lombok.Getter;

public enum WeightFhirResEnum {
 
	CDA_AUTH("ClinicalDocument/authenticator",1.0f),
	CDA_AUTH_REP_ORG("ClinicalDocument/authenticator/representedOrganization",1.0f),
	CDA_AUTH_REP_ORG_PARTOF("ClinicalDocument/authenticator/representedOrganization/asOrganizationPartOf",1.0f),
	CDA_AUTH_REP_ORG_PARTOF_WHOLE_ORG("ClinicalDocument/authenticator/representedOrganization/asOrganizationPartOf/wholeOrganization",1.0f),
	CDA_AUTHOR("ClinicalDocument/author",1.0f),
	CDA_AUTHOR_REP_ORG("ClinicalDocument/author/representedOrganization",1.0f),
	CDA_AUTHOR_REP_ORG_PARTOF("ClinicalDocument/author/representedOrganization/asOrganizationPartOf",1.0f),
	CDA_AUTHOR_REP_ORG_PARTOF_WHOLE_ORG("ClinicalDocument/author/representedOrganization/asOrganizationPartOf/wholeOrganization",1.0f),
	CDA_BODY_ACT_AUTHOR("ClinicalDocument/body/act/author",1.0f),
	CDA_BODY_ACT_AUTHOR_REP_ORG("ClinicalDocument/body/act/author/representedOrganization",1.0f),
	CDA_BODY_ACT_AUTHOR_REP_ORG_PARTOF("ClinicalDocument/body/act/author/representedOrganization/asOrganizationPartOf",1.0f),
	CDA_BODY_ACT_AUTHOR_REP_ORG_PARTOF_WHOLE_ORG("ClinicalDocument/body/act/author/representedOrganization/asOrganizationPartOf/wholeOrganization",1.0f),
	CDA_BODY_ACT_PARTICIPANT("ClinicalDocument/body/act/participant",1.0f),
	CDA_BODY_ACT_PERFOMER("ClinicalDocument/body/act/performer",1.0f),
	CDA_BODY_ACT_REP_ORG("ClinicalDocument/body/act/representedOrganization",1.0f),
	CDA_BODY_ACT_REP_ORG_PARTOF("ClinicalDocument/body/act/representedOrganization/asOrganizationPartOf",1.0f),
	CDA_BODY_ACT_REP_ORG_PARTOF_WHOLE_ORG("ClinicalDocument/body/act/representedOrganization/asOrganizationPartOf/wholeOrganization",1.0f),
	CDA_BODY_ANAMNESI_AUTHOR("clinicalDocument/body/Anamnesi/author",1.0f),
	CDA_BODY_CONSULENZA_OBSERV_PERFOMER("ClinicalDocument/body/Consulenza/Observation/performer",1.0f),
	CDA_BODY_CONSULENZA_OBSERV_SERV_REQ_PARTICIPANT("ClinicalDocument/body/Consulenza/Observation/ServiceRequest/participant",1.0f),
	CDA_BODY_ENTRY_OBSERV_PERFOMER("ClinicalDocument/body/entry/observation/performer",1.0f),
	CDA_BODY_ESAME_OBIETTIVO_AUTHOR("clinicalDocument/body/EsameObiettivo/author",1.0f),
	CDA_BODY_ESAME_RICOVERO_OBS_PERFOMER("ClinicalDocument/body/EsamiDuranteRicovero/Observation/performer",1.0f),
	CDA_BODY_ESAME_RICOVERO_OBS_SERV_REQ_PARTICIPANT("ClinicalDocument/body/EsamiDuranteRicovero/Observation/ServiceRequest/participant",1.0f),
	CDA_BODY_INQUADRAMENTO_CLINICO_AUTHOR("clinicalDocument/body/InquadramentoClinicoIniziale/author",1.0f),
	CDA_BODY_OBSERVATION_AUTHOR("ClinicalDocument/body/observation/author",1.0f),
	CDA_BODY_OBSERVATION_AUTHOR_REP_ORGANIZATION("ClinicalDocument/body/observation/author/representedOrganization",1.0f),
	CDA_BODY_OBSERVATION_AUTHOR_REP_ORGANIZATION_PARTOF("ClinicalDocument/body/observation/author/representedOrganization/asOrganizationPartOf",1.0f),
	CDA_BODY_OBSERVATION_AUTHOR_REP_ORGANIZATION_PARTOF_WHOLE_ORG("ClinicalDocument/body/observation/author/representedOrganization/asOrganizationPartOf/wholeOrganization",1.0f),
	CDA_BODY_OBSERVATION_PARTICIPANT("ClinicalDocument/body/Observation/participant",1.0f),
	CDA_BODY_OBSERVATION_PERFOMER("ClinicalDocument/body/Observation/performer",1.0f),
	CDA_BODY_OBSERVATION_PERFOMER_REP_ORG("ClinicalDocument/body/Observation/performer/representedOrganization",1.0f),
	CDA_BODY_OBSERVATION_PERFOMER_REP_ORG_PARTOF("ClinicalDocument/body/Observation/performer/representedOrganization/asOrganizationPartOf",1.0f),
	CDA_BODY_OBSERVATION_PERFOMER_REP_ORG_WHOLEORG("ClinicalDocument/body/Observation/performer/representedOrganization/wholeOrganization",1.0f),
	CDA_BODY_ORGANIZER_AUTHOR("ClinicalDocument/body/organizer/author",1.0f),
	CDA_BODY_ORGANIZER_AUTHOR_REP_ORG("ClinicalDocument/body/organizer/author/representedOrganization",1.0f),
	CDA_BODY_ORGANIZER_AUTHOR_REP_ORG_PARTOF("ClinicalDocument/body/organizer/author/representedOrganization/asOrganizationPartOf",1.0f),
	CDA_BODY_ORGANIZER_AUTHOR_REP_ORG_PARTOF_WHOLE_ORG("ClinicalDocument/body/organizer/author/representedOrganization/asOrganizationPartOf/wholeOrganization",1.0f),
	CDA_BODY_ORGANIZER_PARTICIPANT("ClinicalDocument/body/organizer/participant",1.5f),
	CDA_BODY_ORGANIZER_PERFOMER("ClinicalDocument/body/organizer/performer",1.5f),
	CDA_BODY_ORGANIZER_PERFOMER_REP_ORG("ClinicalDocument/body/organizer/performer/representedOrganization",1.5f),
	CDA_BODY_ORGANIZER_PERFOMER_REP_ORG_PARTOF("ClinicalDocument/body/organizer/performer/representedOrganization/asOrganizationPartOf",1.5f),
	CDA_BODY_ORGANIZER_PERFOMER_REP_ORG_PARTOF_WHOLE_ORG("ClinicalDocument/body/organizer/performer/representedOrganization/asOrganizationPartOf/wholeOrganization",1.5f),
	CDA_BODY_PROBLEMI_APERTI_AUTHOR("clinicalDocument/body/ProblemiAperti/author",1.5f),
	CDA_BODY_TERAPIA_FARM_PARTICIPANT("ClinicalDocument/body/TERAPIA_FARMACOLOGICA_CONSIGLIATA/participant/",1.5f),
	CDA_BODY_TERAPIA_FARM_IN_AUTHOR("ClinicalDocument/body/TerapiaFarmacologicaIngresso/author",1.5f),
	CDA_BODY_TERAPIA_FARM_RIC_MEDADMIN_PARTICIPANT("ClinicalDocument/body/TerapiaFarmacologicaRicovero/MedAdministration/participant",1.5f),
	CDA_BODY_TERAPIA_FARM_RIC_MEDADMIN_PERFOMER("ClinicalDocument/body/TerapiaFarmacologicaRicovero/MedAdministration/performer",1.5f),
	CDA_BODY_VACCINAZIONE_PARTICIPANT("ClinicalDocument/body/vaccinazione/participant",1.5f),
	CDA_COMP_OF_EE_LOCATION_HEALTH_CARE("ClinicalDocument/componentOf/EncompassingEncounter/location/healthCareFacility",1.5f),
	CDA_COMP_OF_EE_LOCATION_HEALTH_CARE_SER_PROVIDER("ClinicalDocument/componentOf/EncompassingEncounter/location/healthCareFacility/serviceProviderOrganization",1.5f),
	CDA_COMP_OF_EE_LOCATION_HEALTH_CARE_SER_PROVIDER_PARTOF("ClinicalDocument/componentOf/EncompassingEncounter/location/healthCareFacility/serviceProviderOrganization/asOrganizationPartOf",1.5f),
	CDA_COMP_OF_EE_RESPONSIBLE_PARTY("ClinicalDocument/componentOf/EncompassingEncounter/responsibleParty",1.5f),
	CDA_COMP_OF_EE_RESPONSIBLE_PARTY_REP_ORG("ClinicalDocument/componentOf/EncompassingEncounter/responsibleParty/representedOrganization",1.5f),
	CDA_COMP_OF_EE_RESPONSIBLE_PARTY_REP_ORG_PARTOF("ClinicalDocument/componentOf/EncompassingEncounter/responsibleParty/representedOrganization/asOrganizationPartOf",1.5f),
	CDA_COMP_OF_EE_RESPONSIBLE_PARTY_REP_ORG_PARTOF_WHOLE_ORG("ClinicalDocument/componentOf/EncompassingEncounter/responsibleParty/representedOrganization/asOrganizationPartOf/wholeOrganization",1.5f),
	CDA_CUSTODIAN("ClinicalDocument/custodian",1.5f),
	CDA_DATA_ENTERER("ClinicalDocument/dataEnterer",1.5f),
	CDA_DATA_ENTERER_REP_ORG("ClinicalDocument/dataEnterer/representedOrganization",1.5f),
	CDA_DATA_ENTERER_REP_ORG_PARTOF("ClinicalDocument/dataEnterer/representedOrganization/asOrganizationPartOf",1.5f),
	CDA_DATA_ENTERER_REP_ORG_PARTOF_WHOLE_ORG("ClinicalDocument/dataEnterer/representedOrganization/asOrganizationPartOf/wholeOrganization",1.5f),
	CDA_DOC_OF_PERF_REP_ORG_PARTOF("ClinicalDocument/documentationOf/performer/representedOrganization/asOrganizationPartOf",1.5f),
	CDA_DOC_OF_SERVICE_EVENT_PERFOMER("ClinicalDocument/documentationOf/serviceEvent/performer",1.5f),
	CDA_DOC_OF_SERVICE_EVENT_PERFOMER_REP_ORG("ClinicalDocument/documentationOf/serviceEvent/performer/representedOrganization",1.5f),
	CDA_DOC_OF_SERVICE_EVENT_PERFOMER_REP_ORG_PARTOF("ClinicalDocument/documentationOf/serviceEvent/performer/representedOrganization/asOrganizationPartOf",1.5f),
	CDA_DOC_OF_SERVICE_EVENT_PERFOMER_REP_ORG_PARTOF_WHOLE_ORG("ClinicalDocument/documentationOf/serviceEvent/performer/representedOrganization/asOrganizationPartOf/wholeOrganization",1.5f),
	CDA_ENCOUNTER_PARTICIPANT("ClinicalDocument/encounterParticipant",1.5f),
	CDA_ENCOUNTER_PARTICIPANT_SCOPING_ORG("ClinicalDocument/encounterParticipant/scopingOrganization",1.5f),
	CDA_ENCOUNTER_PARTICIPANT_SCOPING_ORG_PARTOF("ClinicalDocument/encounterParticipant/scopingOrganization/asOrganizationPartOf",1.5f),
	CDA_ENCOUNTER_PARTICIPANT_SCOPING_ORG_PARTOF_WHOLE_ORG("ClinicalDocument/encounterParticipant/scopingOrganization/asOrganizationPartOf/wholeOrganization",1.5f),
	CDA_INFORMANT("ClinicalDocument/informant",1.5f),
	CDA_INFORMANT_REP_ORG("ClinicalDocument/informant/representedOrganization",1.5f),
	CDA_INFORMANT_REP_ORG_PARTOF("ClinicalDocument/informant/representedOrganization/asOrganizationPartOf",1.5f),
	CDA_INFORMANT_REP_ORG_PARTOF_WHOLE_ORG("ClinicalDocument/informant/representedOrganization/asOrganizationPartOf/wholeOrganization",1.5f),
	CDA_INFORMANT_REC("ClinicalDocument/informationRecipient",1.5f),
	CDA_INFORMANT_REC_RECEIVER_ORG("ClinicalDocument/informationRecipient/receivedOrganization",1.5f),
	CDA_INFORMANT_REC_RECEIVER_ORG_PARTOF("ClinicalDocument/informationRecipient/receivedOrganization/asOrganizationPartOf",1.5f),
	CDA_INFORMANT_REC_RECEIVER_ORG_PARTOF_WHOLE_ORG("ClinicalDocument/informationRecipient/receivedOrganization/asOrganizationPartOf/wholeOrganization",1.5f),
	CDA_LEGAL_AUTH("ClinicalDocument/legalAuthenticator",1.5f),
	CDA_LEGAL_AUTH_REP_ORG("ClinicalDocument/legalAuthenticator/representedOrganization",1.5f),
	CDA_LEGAL_AUTH_REP_ORG_PARTOF("ClinicalDocument/legalAuthenticator/representedOrganization/asOrganizationPartOf",1.5f),
	CDA_LEGAL_AUTH_REP_ORG_PARTOF_WHOLE_ORG("ClinicalDocument/legalAuthenticator/representedOrganization/asOrganizationPartOf/wholeOrganization",1.5f),
	CDA_PARTICIPANT("ClinicalDocument/participant",1.5f),
	CDA_PARTICIPANT_REP_ORG("ClinicalDocument/participant/representedOrganization",1.5f),
	CDA_PARTICIPANT_REP_ORG_PARTOF("ClinicalDocument/participant/representedOrganization/asOrganizationPartOf",1.5f),
	CDA_PARTICIPANT_REP_ORG_PARTOF_WHOLE_ORG("ClinicalDocument/participant/representedOrganization/asOrganizationPartOf/wholeOrganization",1.5f),
	CDA_PARTICIPANT_SCOPING_ORG("ClinicalDocument/participant/scopingOrganization",1.5f),
	CDA_PARTICIPANT_SCOPING_ORG_PARTOF("ClinicalDocument/participant/scopingOrganization/asOrganizationPartOf",1.5f),
	CDA_PARTICIPANT_SCOPING_ORG_PARTOF_WHOLE_ORG("ClinicalDocument/participant/scopingOrganization/asOrganizationPartOf/wholeOrganization",1.5f),
	CDA_PATICIPANT("ClinicalDocument/paticipant",1.5f),
	CDA_PATICIPANT_SCOPING_ORG("ClinicalDocument/paticipant/scopingOrganization",1.5f),
	CDA_PATICIPANT_SCOPING_ORG_PARTOF("ClinicalDocument/paticipant/scopingOrganization/asOrganizationPartOf",1.5f),
	CDA_PATICIPANT_SCOPING_ORG_PARTOF_WHOLE_ORG("ClinicalDocument/paticipant/scopingOrganization/asOrganizationPartOf/wholeOrganization",1.5f),
	CDA_REC_TARGET_PATIENTROLE_PATIENT_GUARDIAN_ORG("ClinicalDocument/recordTarget/PatientRole/patient/guardian/guardianOrganization",1.5f),
	CDA_REC_TARGET_PATIENTROLE_PATIENT_GUARDIAN_ORG_PARTOF("ClinicalDocument/recordTarget/PatientRole/patient/guardian/guardianOrganization/asOrganizationPartOf",1.5f),
	CDA_REC_TARGET_PATIENTROLE_PATIENT_GUARDIAN_ORG_PARTOF_WHOLE_ORG("ClinicalDocument/recordTarget/PatientRole/patient/guardian/guardianOrganization/asOrganizationPartOf/wholeOrganization",1.5f),
	CDA_REC_TARGET_PATIENTROLE_PATIENT_PROV_ORG("ClinicalDocument/recordTarget/PatientRole/patient/providerOrganization",1.5f),
	CDA_REC_TARGET_PATIENTROLE_PATIENT_PROV_ORG_PARTOF("ClinicalDocument/recordTarget/PatientRole/patient/providerOrganization/asOrganizationPartOf",1.5f),
	CDA_REC_TARGET_PATIENTROLE_PATIENT_PROV_ORG_PARTOF_WHOLE_ORG("ClinicalDocument/recordTarget/PatientRole/patient/providerOrganization/asOrganizationPartOf/wholeOrganization",1.5f),
	BODY_MANUFACTURER_ORG("body/manufacturerOrganization",1.5f),
	BODY_PARTICIPANT("body/participant",1.5f);
	 
	@Getter
	private String name;
	
		
	@Getter
	private float weight;

	private WeightFhirResEnum(String inName, float inWeight) {
		name = inName;
		weight = inWeight;
	}
	
	public static WeightFhirResEnum fromValue(final String value) {
		WeightFhirResEnum output = null;
        for (final WeightFhirResEnum valueEnum : WeightFhirResEnum.values()) {
            if (valueEnum.getName().equalsIgnoreCase(value)) {
                output = valueEnum;
                break;
            }

        }

        return output;
    }

}
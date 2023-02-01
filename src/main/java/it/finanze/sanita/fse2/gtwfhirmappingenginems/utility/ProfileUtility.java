/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package it.finanze.sanita.fse2.gtwfhirmappingenginems.utility;

import it.finanze.sanita.fse2.gtwfhirmappingenginems.config.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ProfileUtility {
	
    @Autowired
    private Environment environment;

    public boolean isTestProfile() {
        if (environment != null && environment.getActiveProfiles().length > 0) {
            return environment.getActiveProfiles()[0].toLowerCase().contains(Constants.Profile.TEST);
        }
        return false;
    }

    public boolean isEngineTestProfile() {
        if (environment != null && environment.getActiveProfiles().length > 0) {
            return environment.getActiveProfiles()[0].toLowerCase().contains(Constants.Profile.TEST_ENGINE);
        }
        return false;
    }

}

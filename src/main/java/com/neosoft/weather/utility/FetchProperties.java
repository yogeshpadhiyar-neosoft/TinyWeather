package com.neosoft.weather.utility;

import java.util.Properties;

/**
 * Fetching data from properties file
 */

public class FetchProperties {

    /**
     * This method use for fetch properties from Default.Properties file.
     * @param propertiesName
     * @return
     */
    public String fetchPropertiesFromDefault(String propertiesName){
        String propValue = "";
        Properties properties = new Properties();
        try {
            properties.load(this.getClass().getClassLoader().getResourceAsStream("default.properties"));
            propValue = properties.getProperty(propertiesName);
        }catch (Exception e){
            e.getMessage();
        }
        return propValue;
    }
}

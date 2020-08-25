package com.neosoft.weather.utility;

import com.neosoft.weather.appConstant.AppMessage;
import com.neosoft.weather.exception.CustomException;
import com.neosoft.weather.requestModel.WorkInfo;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Generate diffrent URL
 */
public class URLGenerator {

    /**
     * Take @WorkInfo and get city name , state code and country code from is and generate URL
     * Here Statecode and country code is not mandatory.
     * @param workInfo
     * @return
     */
    public static String getURL(WorkInfo workInfo){
        List<String> errors = new ArrayList<>();
        FetchProperties fetchProperties = new FetchProperties();
        String q;
        if (workInfo.getCity() == null) {
            q = fetchProperties.fetchPropertiesFromDefault(AppMessage.CITY);
            if (q == null) {
                errors.add(AppMessage.NF_CITY_PROP);
            }
        }
        else{
            q = workInfo.getCity();
            if (workInfo.getStateCode() != null) {
                q = q + "," + workInfo.getStateCode();
            }
            if (workInfo.getCountryCode() != null) {
                q = q + "," + workInfo.getCountryCode();
            }
        }
        String api_key = fetchProperties.fetchPropertiesFromDefault(AppMessage.API_KEY);
        if (api_key == null) {
            errors.add(AppMessage.NF_API_KEY_PROP);
        }
        String baseUrl = fetchProperties.fetchPropertiesFromDefault(AppMessage.BASE_URL);
        if (baseUrl == null) {
            errors.add(AppMessage.NF_BASEURL_PROP);
        }
        if(!errors.isEmpty()){
            throw new CustomException(HttpStatus.BAD_REQUEST,errors);
        }
        String url = baseUrl + "q=" + q + "&appid=" + api_key;
        return url;
    }
}


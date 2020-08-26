package com.neosoft.weather.appConstant;

/**
 * Application Constant
 */

public class AppMessage {

    public static final String SUCCESS = "successful";
    public static final String FAIL = "fail";
    public static final String CITY = "city";
    public static final String API_KEY = "api_key";
    public static final String BASE_URL = "base_url";
    public static final String START_DAY = "start_day";
    public static final String END_DAY = "end_day";

    public static final String MANDATORY_FIELD = "City Name , Start Time and End Time mandatory filed.";
    public static final String START_TIME_VALIDATION = "Start Time not valid. Enter In 24 hour format (HH:mm).";
    public static final String END_TIME_VALIDATION = "End Time not valid. Enter In 24 hour format (HH:mm).";
    public static final String NF_CITY_PROP = "Properties 'city' not found in properties file.";
    public static final String NF_API_KEY_PROP = "Properties 'api_key' not found in properties file.";
    public static final String NF_BASEURL_PROP = "Properties 'base_url' not found in properties file.";
    public static final String NF_DAY_PROP = "Properties field 'startday' or 'endday ' missing.";
    public static final String DESCRIPTION = "Next Three day average Temperature and Humidity";
    public static final String FAIL_DESCRIPTION = "Disable to find Next Three day average Temperature and Humidity due to some error is like :";
    public static final String INTERNET_ERROR = "Unable to reach Internet connection error. please check internet.";
}

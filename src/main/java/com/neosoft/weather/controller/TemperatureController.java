package com.neosoft.weather.controller;

import com.neosoft.weather.appConstant.AppMessage;
import com.neosoft.weather.exception.CustomException;
import com.neosoft.weather.requestModel.WorkInfo;
import com.neosoft.weather.responseModel.AvgTempAndHumidity;
import com.neosoft.weather.responseModel.TinyWeatherResponse;
import com.neosoft.weather.service.TemperatureAvgService;
import com.neosoft.weather.validation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Rest controller of Tiny weather project
 */

@RestController
@RequestMapping("/TinyWeather")
public class TemperatureController {
    private final TemperatureAvgService temperatureAvgService;

    public TemperatureController(TemperatureAvgService temperatureAvgService) {
        this.temperatureAvgService = temperatureAvgService;
    }


    /**
     * This API is call for the find average next three day temperature and humidity
     * User need to give payload of WorkInfo that contain city name , start time and end time of employee(mandatory)
     * also user can give state code and country code.
     * As User Response contain average of next three day temperature and humidity
     * with Api response time , description and Http status code.
     * @param workInfo
     * @return
     */
    @PostMapping("/avgTH")
    public ResponseEntity<TinyWeatherResponse> avgTempAndHumidity(@RequestBody WorkInfo workInfo){
        long apiCallTime = System.currentTimeMillis();
        ResponseEntity<TinyWeatherResponse> responseEntity = null;
        try {
            if(RequestBodyValidation.validator(workInfo)){
                List<AvgTempAndHumidity> avgTempAndHumidityList = temperatureAvgService.fetchAvgTempAndHM(workInfo);
                TinyWeatherResponse tinyWeatherResponse = new TinyWeatherResponse();
                tinyWeatherResponse.setAvgTempAndHumidityList(avgTempAndHumidityList);
                tinyWeatherResponse.setDescription(AppMessage.DESCRIPTION);

                long responseTime = (System.currentTimeMillis()-apiCallTime);
                tinyWeatherResponse.setResponseTime(responseTime);
                responseEntity = RequestBodyValidation.responseBuilder(tinyWeatherResponse);
            }
        }catch (CustomException e){
            responseEntity = RequestBodyValidation.responseError(e);
        }
        return responseEntity;
    }

}

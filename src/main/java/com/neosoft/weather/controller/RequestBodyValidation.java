package com.neosoft.weather.controller;

import com.neosoft.weather.appConstant.AppMessage;
import com.neosoft.weather.appConstant.Regex;
import com.neosoft.weather.exception.CustomException;
import com.neosoft.weather.requestModel.WorkInfo;
import com.neosoft.weather.responseModel.TinyWeatherResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *  Payload validation
 */

public abstract class RequestBodyValidation {

    /**
     * Check all validation of payload @WorkInfo
     * @param workInfo
     * @return
     */
    protected  boolean validator(WorkInfo workInfo){

        List<String> errors = new ArrayList<>();
        if(!nullValidator(workInfo)){
            errors.add(AppMessage.MANDATORY_FIELD);
        }
        if(!timeValidator(workInfo.getStartTime()) ){
            errors.add(AppMessage.START_TIME_VALIDATION);
        }
        if (!timeValidator(workInfo.getEndTime())){
            errors.add(AppMessage.END_TIME_VALIDATION);
        }
        if(errors.isEmpty()){
            return true;
        }else {
            CustomException exception = new CustomException();
            exception.setHttpStatus(HttpStatus.BAD_REQUEST);
            exception.setErrors(errors);
            throw exception;
        }
    }

    /**
     * NUll pointer validation
     * Mandatory filed validation
     * @param workInfo
     * @return
     */
    protected  boolean nullValidator(WorkInfo workInfo){
        return  workInfo.getCity()!=null &&
                workInfo.getStartTime()!=null &&
                workInfo.getEndTime()!=null;
    }


    /**
     * Time validation with Regex
     * @param time
     * @return
     */
    protected boolean timeValidator(String time){
        return time.matches(Regex.TIME_VALID);
    }

    /**
     * Start time end time comparition
     * Not use for night shift
     * @param start
     * @param end
     * @return
     */
    protected boolean timeCompare(String start , String end){
        LocalTime startT = LocalTime.parse(start);
        LocalTime endT = LocalTime.parse(end);
        return startT.compareTo(endT)<0;
    }

    /**
     * Generic response
     * @param tinyWeatherResponse
     * @return
     */
    protected ResponseEntity<Object> responseBuilder(TinyWeatherResponse tinyWeatherResponse){
        tinyWeatherResponse.setCode(200);
        tinyWeatherResponse.setMessage(AppMessage.SUCCESS);
        return new ResponseEntity<>(tinyWeatherResponse , HttpStatus.OK);
    }

    /**
     * Generatic response for error
     * @param e
     * @return
     */
    protected ResponseEntity<Object> responseError(CustomException e){
        TinyWeatherResponse tinyWeatherResponse = new TinyWeatherResponse();
        tinyWeatherResponse.setCode(400);
        tinyWeatherResponse.setMessage(AppMessage.FAIL);
        tinyWeatherResponse.setDescription(e.getErrors().toString());
        return new ResponseEntity<>(e.getErrors(),e.getHttpStatus());
    }
}

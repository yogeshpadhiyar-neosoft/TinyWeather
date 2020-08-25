package com.neosoft.weather.service;

import com.neosoft.weather.requestModel.WorkInfo;
import com.neosoft.weather.responseModel.AvgTempAndHumidity;
import com.neosoft.weather.responseModel.WeatherAPIResponse;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Services
 */

@Service
public interface TemperatureAvgService {

    /**
     * Service take @WorkInfo payload for city name , state code ad country code
     * to find data from weather API
     * @param workInfo
     * @return
     */
    WeatherAPIResponse getFullWeatherDetails(WorkInfo workInfo);


    /**
     * Service take @WorkInfo payload with start time and end time of employee job
     * As a response give next three day average temperature and humidity
     * with separate employee working hour and non working hour average.
     * @param workInfo
     * @return
     */
    List<AvgTempAndHumidity> fetchAvgTempAndHM(WorkInfo workInfo);
}

package com.neosoft.weather.service;

import com.neosoft.weather.responseModel.WeatherDetails;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Arithmetic service for average , sum all arithmetic function.
 */

@Service
public interface ArithmeticService {

    /**
     * In List of data have all data have particular time wise max temperature
     * Take that temperature max data and average it value using Stream Api.
     * @param data
     * @return
     */
    double getAvgMaxTemp(List<WeatherDetails> data);

    /**
     * In List of data have all data have particular time wise min temperature
     * Take that temperature min data and average it value using Stream Api.
     * @param data
     * @return
     */
    double getAvgMinTemp(List<WeatherDetails> data);

    /**
     * In List of data have all data have particular time wise humidity
     * Take that humidity data and average it value using Stream Api.
     * @param data
     * @return
     */
    double getAvgHumidity(List<WeatherDetails> data);
}

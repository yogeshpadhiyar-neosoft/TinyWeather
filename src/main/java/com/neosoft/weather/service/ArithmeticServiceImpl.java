package com.neosoft.weather.service;

import com.neosoft.weather.responseModel.WeatherDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArithmeticServiceImpl implements ArithmeticService{


    @Override
    public double getAvgMaxTemp(List<WeatherDetails> workingHourData) {
        return workingHourData.stream().mapToDouble(data -> data.getMain().getTemp_max()).average().orElse(0.0);
    }

    @Override
    public double getAvgMinTemp(List<WeatherDetails> workingHourData) {
        return workingHourData.stream().mapToDouble(data -> data.getMain().getTemp_min()).average().orElse(0.0);
    }

    @Override
    public double getAvgHumidity(List<WeatherDetails> workingHourData) {
        return workingHourData.stream().mapToDouble(data -> data.getMain().getHumidity()).average().orElse(0.0);
    }
}

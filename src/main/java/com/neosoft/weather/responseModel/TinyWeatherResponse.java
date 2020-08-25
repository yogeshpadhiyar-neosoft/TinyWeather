package com.neosoft.weather.responseModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TinyWeatherResponse {
    private int code;
    private String message;
    private List<AvgTempAndHumidity> avgTempAndHumidityList;
    private long responseTime;
    private String description;
}

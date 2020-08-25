package com.neosoft.weather.responseModel;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WeatherMain {

    private double temp;
    private double  feels_like;
    private double  temp_min;
    private double  temp_max;
    private int pressure;
    private int sea_level;
    private int grnd_level;
    private int humidity;
    private double temp_kf;

}

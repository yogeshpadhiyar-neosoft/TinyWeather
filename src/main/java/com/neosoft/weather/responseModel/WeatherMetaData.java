package com.neosoft.weather.responseModel;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WeatherMetaData {

    private int id;
    private String main;
    private String description;
    private String icon;
}

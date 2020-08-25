package com.neosoft.weather.responseModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class WeatherDetails {

    private String dt;

    @JsonProperty("main")
    private WeatherMain main;

    @JsonProperty("weather")
    private List<WeatherMetaData> weather;

    @JsonProperty("clouds")
    private Cloud cloud;

    @JsonProperty("wind")
    private Wind wind;

    private long visibility;

    private double pop;

    @JsonProperty("sys")
    private Sys sys;

    @JsonProperty("dt_txt")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime;
}

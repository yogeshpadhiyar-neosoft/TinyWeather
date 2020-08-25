package com.neosoft.weather.responseModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class WeatherAPIResponse {

    private String cod;
    private int message;
    private int cnt;

    @JsonProperty("list")
    private List<WeatherDetails> weatherDetails;

    @JsonProperty("city")
    private City city;
}

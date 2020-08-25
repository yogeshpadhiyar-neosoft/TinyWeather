package com.neosoft.weather.responseModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class City {

    private int id;
    private String name;
    @JsonProperty("coord")
    private Coord coord;
    private String country;
    private int population;
    private int timezone;
    private int sunrise;
    private int sunset;
}

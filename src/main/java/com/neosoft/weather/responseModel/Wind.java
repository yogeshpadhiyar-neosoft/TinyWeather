package com.neosoft.weather.responseModel;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Wind {
    private double speed;
    private int deg;
}

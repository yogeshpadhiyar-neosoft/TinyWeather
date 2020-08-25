package com.neosoft.weather.responseModel;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AvgTempAndHumidity {
    private int day;

    private double workAvgMaxTemp;
    private double workAvgMinTemp;
    private double workAvgHumidity;

    private double nonWorkAvgMaxTemp;
    private double nonWorkAvgMinTemp;
    private double nonWorkAvgHumidity;
}

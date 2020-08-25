package com.neosoft.weather.requestModel;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkInfo {
    private String startTime;
    private String endTime;
    private String city;
    private String stateCode;
    private String countryCode;
}

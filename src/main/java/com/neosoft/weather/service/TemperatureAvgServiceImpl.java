package com.neosoft.weather.service;

import com.neosoft.weather.appConstant.AppMessage;
import com.neosoft.weather.exception.CustomException;
import com.neosoft.weather.requestModel.WorkInfo;
import com.neosoft.weather.responseModel.AvgTempAndHumidity;
import com.neosoft.weather.responseModel.WeatherAPIResponse;
import com.neosoft.weather.responseModel.WeatherDetails;
import com.neosoft.weather.utility.FetchProperties;
import com.neosoft.weather.utility.URLGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class TemperatureAvgServiceImpl extends ArithmeticServiceImpl implements TemperatureAvgService {


    @Override
    public WeatherAPIResponse getFullWeatherDetails(WorkInfo workInfo) {
        String url = URLGenerator.getURL(workInfo);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, WeatherAPIResponse.class );
    }

    @Override
    public List<AvgTempAndHumidity> fetchAvgTempAndHM(WorkInfo workInfo) {
        List<AvgTempAndHumidity> dayViseAvgTempAndHM = new ArrayList<>();

        WeatherAPIResponse weatherAPIResponse = getFullWeatherDetails(workInfo);

        Map<Integer , List<WeatherDetails>> dayViseData = new HashMap<>();
        FetchProperties fetchProperties = new FetchProperties();

        int startDay = Integer.parseInt(fetchProperties.fetchPropertiesFromDefault(AppMessage.START_DAY));
        int endDay = Integer.parseInt(fetchProperties.fetchPropertiesFromDefault(AppMessage.END_DAY));
        if(startDay==0||endDay==0){
            throw new CustomException(HttpStatus.BAD_REQUEST, Arrays.asList(AppMessage.NF_DAY_PROP));
        }
        for(int i =startDay; i<=endDay;i++) {
            List<WeatherDetails> fullDayData = getAllThreeData(weatherAPIResponse.getWeatherDetails() , LocalDate.now().plusDays(i));
            dayViseData.put(i , fullDayData);
        }

        for (Map.Entry<Integer, List<WeatherDetails>> entry : dayViseData.entrySet()) {
            List<WeatherDetails> workingHourData = getWorkingHourData(entry.getValue() , workInfo);
            List<WeatherDetails> tempFullData = entry.getValue();
            tempFullData.removeAll(workingHourData);
            List<WeatherDetails> nonWorkingHourData = tempFullData;
            AvgTempAndHumidity dayAvg = AvgDataParticularDay(entry , workingHourData ,  nonWorkingHourData);

            dayViseAvgTempAndHM.add(dayAvg);
        }
        return dayViseAvgTempAndHM;
    }

    private List<WeatherDetails> getAllThreeData(List<WeatherDetails> weatherDetails, LocalDate plusDays) {
        return weatherDetails.stream().filter(data -> data.getDateTime().getDayOfMonth() == plusDays.getDayOfMonth()).collect(Collectors.toList());
    }

    private List<WeatherDetails> getWorkingHourData(List<WeatherDetails> fullData, WorkInfo workInfo) {
        return fullData.stream().filter(data -> data.getDateTime().toLocalTime().isAfter(LocalTime.parse(workInfo.getStartTime())) && data.getDateTime().toLocalTime().isBefore(LocalTime.parse(workInfo.getEndTime()))).collect(Collectors.toList());
    }

    private AvgTempAndHumidity AvgDataParticularDay(Map.Entry<Integer, List<WeatherDetails>> entry, List<WeatherDetails> workingHourData, List<WeatherDetails> nonWorkingHourData) {
        AvgTempAndHumidity avgTempAndHumidity = new AvgTempAndHumidity();
        avgTempAndHumidity.setDay(entry.getKey());

        avgTempAndHumidity.setWorkAvgMaxTemp(getAvgMaxTemp(workingHourData));
        avgTempAndHumidity.setWorkAvgMinTemp(getAvgMinTemp(workingHourData));
        avgTempAndHumidity.setWorkAvgHumidity(getAvgHumidity(workingHourData));

        avgTempAndHumidity.setNonWorkAvgMaxTemp(getAvgMaxTemp(nonWorkingHourData));
        avgTempAndHumidity.setNonWorkAvgMinTemp(getAvgMinTemp(nonWorkingHourData));
        avgTempAndHumidity.setNonWorkAvgHumidity(getAvgHumidity(nonWorkingHourData));
        return avgTempAndHumidity;
    }
}

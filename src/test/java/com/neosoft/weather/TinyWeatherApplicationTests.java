package com.neosoft.weather;

import com.neosoft.weather.appConstant.AppMessage;
import com.neosoft.weather.controller.TemperatureController;
import com.neosoft.weather.requestModel.WorkInfo;
import com.neosoft.weather.responseModel.AvgTempAndHumidity;
import com.neosoft.weather.responseModel.TinyWeatherResponse;
import com.neosoft.weather.service.TemperatureAvgService;
import com.neosoft.weather.validation.RequestBodyValidation;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Test cases
 */

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = TemperatureController.class)
class TinyWeatherApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TemperatureAvgService temperatureAvgService;
    @MockBean
    RequestBodyValidation requestBodyValidation;

    String url = "/TinyWeather";
    TinyWeatherResponse tinyWeatherResponsePos;
    List<AvgTempAndHumidity> avgTempAndHumidityList = new ArrayList<>();

    /**
     * Setup all the required entity
     */
    @Before
    public void setup() {
        tinyWeatherResponsePos = new TinyWeatherResponse();
        tinyWeatherResponsePos.setCode(200);
        tinyWeatherResponsePos.setMessage(AppMessage.SUCCESS);
        avgTempAndHumidityList = new ArrayList<>();
        tinyWeatherResponsePos.setAvgTempAndHumidityList(avgTempAndHumidityList);
        tinyWeatherResponsePos.setResponseTime(0);
        tinyWeatherResponsePos.setDescription(AppMessage.DESCRIPTION);


    }

    /**
     * Positive test case for URL "/TinyWeather/avgTH/{city}/{startTime}/{endTime}"
     * with status check 'OK'(200)
     * @throws Exception
     */
    @Test
    public void avgTempAndHumidityPosTest() throws Exception {
        when(temperatureAvgService.fetchAvgTempAndHM(any(WorkInfo.class))).thenReturn(avgTempAndHumidityList);

        mockMvc.perform(MockMvcRequestBuilders.get(url+"/avgTH/London/08:59/12:10")
                .characterEncoding("UTF-8"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(temperatureAvgService).fetchAvgTempAndHM(any(WorkInfo.class));
    }


    /**
     * Invalid city name ,  start time and end time testing.
     * Negative test case for URL "/TinyWeather/avgTH/{city}/{startTime}/{endTime}"
     * If we dont pass specific time format HH:mm with 24 hour format in URL
     * Then throw error with status code 'BAD_REQUEST'(400)
     * @throws Exception
     */
    @Test
    public void avgTempAndHumidityNegTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(url+"/avgTH/London/24:59/32:10")
                .characterEncoding("UTF-8"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }


}

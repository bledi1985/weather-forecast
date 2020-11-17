package com.bledi.weatherforecast.rest;

import com.bledi.weatherforecast.model.AverageDataDto;
import com.bledi.weatherforecast.model.WrapperAverageData;
import com.bledi.weatherforecast.service.WeatherForecastService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class WeatherForecastControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  WeatherForecastService weatherForecastService;


  @Test
  public void test_whenServiceThrowsException_statusShouldBe404() throws Exception {
    Mockito.when(weatherForecastService.getAverageMaxMinTemp(Mockito.anyString()))
        .thenThrow(RuntimeException.class);
    mockMvc.perform(MockMvcRequestBuilders.get("/weather-forecast/v1.0/city/london"))
        .andExpect(MockMvcResultMatchers.status().is(417));
  }

  @Test
  public void test_whenServiceReturnDat_statusShouldBe200() throws Exception {
    AverageDataDto averageDataDto = new AverageDataDto();
    averageDataDto.setAverageHumidityNotWorkingHours(BigDecimal.valueOf(10));
    averageDataDto.setAverageHumidityWorkingHours(BigDecimal.valueOf(5));
    averageDataDto.setAverageMaxTempNotWorkingHours(BigDecimal.valueOf(15));
    averageDataDto.setAverageMaxTempWorkingHours(BigDecimal.valueOf(20));
    averageDataDto.setAverageMinTempWorkingHours(BigDecimal.valueOf(2));
    averageDataDto.setAverageMinTempNotWorkingHours(BigDecimal.valueOf(10));
    averageDataDto.setDate(LocalDate.now());

    List<AverageDataDto> averageDataDtoList = new ArrayList<>();
    averageDataDtoList.add(averageDataDto);

    WrapperAverageData wrapperAverageData = new WrapperAverageData("london", averageDataDtoList,
        9,
        18);

    Mockito.when(weatherForecastService.getAverageMaxMinTemp(Mockito.anyString()))
        .thenReturn(averageDataDtoList);

    MvcResult result = mockMvc
        .perform(MockMvcRequestBuilders.get("/weather-forecast/v1.0/city/london"))
        .andExpect(MockMvcResultMatchers.status().is(200)).andReturn();
    log.info(result.getResponse().getContentAsString());
    Assert.assertTrue(
        result.getResponse().getContentAsString().trim().contains("{\"city\":\"london\""));
    Assert.assertTrue(
        result.getResponse().getContentAsString().trim().contains("{\"date\":\"2020-11-17\""));
    Assert.assertTrue(
        result.getResponse().getContentAsString().trim().contains("\"avg_max_temp_wrk_hours\":20"));

  }


}
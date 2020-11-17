package com.bledi.weatherforecast.service;

import com.bledi.weatherforecast.configuration.WeatherForecastConfigProperties;
import com.bledi.weatherforecast.model.AverageDataDto;
import com.bledi.weatherforecast.model.ForecastFiveDayDTO;
import com.bledi.weatherforecast.model.ForecastFiveDayHourlyDataDTO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherForecastServiceImpl implements WeatherForecastService {

  private final RestTemplate restTemplate;
  private final WeatherForecastConfigProperties weatherForecastConfigProperties;
  private static final String APP_ID = "appid";

  public WeatherForecastServiceImpl(RestTemplate restTemplate,
      WeatherForecastConfigProperties weatherForecastConfigProperties) {
    this.restTemplate = restTemplate;
    this.weatherForecastConfigProperties = weatherForecastConfigProperties;
  }

  @Override
  public List<AverageDataDto> getAverageMaxMinTemp(String city) {
    ForecastFiveDayDTO forecastFiveDayDTO = restTemplate
        .getForObject(getUrl(city), ForecastFiveDayDTO.class);
    List<AverageDataDto> averageDataDtoList = new ArrayList<>();

    for (LocalDate refDate = LocalDate.now().plusDays(1);
        refDate.isBefore(LocalDate.now().plusDays(4));
        refDate = refDate.plusDays(1)) {

      LocalDate finalRefDate = refDate;
      AverageDataDto averageDataDto = new AverageDataDto();
      forecastFiveDayDTO.getHourlyDataList().stream()
          .filter(i -> i.getDateTime().toLocalDate().equals(finalRefDate)).forEach(el ->
          sumValues(el, averageDataDto));
      averageDataDto.calculate();
      averageDataDtoList.add(averageDataDto);
    }
    return averageDataDtoList;
  }

  private void sumValues(ForecastFiveDayHourlyDataDTO el, AverageDataDto averageDataDto) {
    averageDataDto.setDate(el.getDateTime().toLocalDate());
    if (el.isWorkingHours(weatherForecastConfigProperties.getWorkinghourstart(),
        weatherForecastConfigProperties.getWorkinghourend())) {
      averageDataDto.setTotalMaxTempWorkingHours(
          averageDataDto.getTotalMaxTempWorkingHours().add(el.getData().getTemp_max()));
      averageDataDto.setTotalMinTempWorkingHours(
          averageDataDto.getTotalMinTempWorkingHours().add(el.getData().getTemp_min()));
      averageDataDto.setTotalHumidityWorkingHours(
          averageDataDto.getTotalHumidityWorkingHours().add(el.getData().getHumidity()));
      averageDataDto.setTotalWorkingHours(averageDataDto.getTotalWorkingHours() + 1);
    } else {
      averageDataDto.setTotalMaxTempNotWorkingHours(
          averageDataDto.getTotalMaxTempNotWorkingHours().add(el.getData().getTemp_max()));
      averageDataDto.setTotalMinTempNotWorkingHours(
          averageDataDto.getTotalMinTempNotWorkingHours().add(el.getData().getTemp_min()));
      averageDataDto.setTotalHumidityNotWorkingHours(
          averageDataDto.getTotalHumidityNotWorkingHours().add(el.getData().getHumidity()));
      averageDataDto.setTotalNotWorkingHours(averageDataDto.getTotalNotWorkingHours() + 1);
    }
  }

  public String getUrl(String city) {
    return String.format(
        weatherForecastConfigProperties.getUrl().concat("?" + APP_ID + "=%s").concat("&q=%s")
            .concat("&units=metric"), weatherForecastConfigProperties.getAppid(), city);
  }
}

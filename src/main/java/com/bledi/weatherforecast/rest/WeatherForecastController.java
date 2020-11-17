package com.bledi.weatherforecast.rest;


import com.bledi.weatherforecast.configuration.WeatherForecastConfigProperties;
import com.bledi.weatherforecast.model.AverageDataDto;
import com.bledi.weatherforecast.model.ForecastFiveDayDTO;
import com.bledi.weatherforecast.model.WrapperAverageData;
import com.bledi.weatherforecast.service.WeatherForecastService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import springfox.documentation.spring.web.json.Json;

@RestController
@Api(value = "/api/weather-forecast")
@Slf4j
@RequestMapping("/weather-forecast")
public class WeatherForecastController {


  private final WeatherForecastService weatherForecastService;
  private final WeatherForecastConfigProperties weatherForecastConfigProperties;

  public WeatherForecastController(WeatherForecastService weatherForecastService,
      WeatherForecastConfigProperties weatherForecastConfigProperties) {
    this.weatherForecastService = weatherForecastService;
    this.weatherForecastConfigProperties = weatherForecastConfigProperties;
  }

  @ApiOperation(value = "Forecast", notes =
      "This api provides the following information for the next 3 days:\n"
          + "average maximum/minimum temperatures and humidity during the working hours;\n"
          + "average maximum/minimum temperatures and humidity outside the working hours.", response = ForecastFiveDayDTO.class)
  @GetMapping(value = "/v1.0/city/{city}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<WrapperAverageData> getAverageMaxMinTempNextTheeDays(@ApiParam(required = true, value = "The city for which you want to search the forecast ", example = "London")
  @PathVariable(required = true) String city) {
    log.info("Searching forecast weather for {}", city);
    try {
      List<AverageDataDto> averageDataDtoList = weatherForecastService.getAverageMaxMinTemp(city);
      WrapperAverageData wrapperAverageData = new WrapperAverageData(city, averageDataDtoList,
          weatherForecastConfigProperties.getWorkinghourstart(),
          weatherForecastConfigProperties.getWorkinghourend());
      return ResponseEntity.ok(wrapperAverageData);
    } catch (Exception e) {
      log.error("something wrong happened {}", e.getMessage());
      return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
    }

  }
}

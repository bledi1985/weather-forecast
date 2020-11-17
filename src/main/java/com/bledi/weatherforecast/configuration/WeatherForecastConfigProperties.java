package com.bledi.weatherforecast.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "openweather")
public class WeatherForecastConfigProperties {

  private String appid;
  private String url;
  private int workinghourstart;
  private int workinghourend;

}

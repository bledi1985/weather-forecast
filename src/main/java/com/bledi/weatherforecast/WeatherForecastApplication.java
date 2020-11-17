package com.bledi.weatherforecast;

import com.bledi.weatherforecast.configuration.WeatherForecastConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(WeatherForecastConfigProperties.class)
public class WeatherForecastApplication {

  public static void main(String[] args) {
    SpringApplication.run(WeatherForecastApplication.class, args);
  }

}

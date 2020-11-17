package com.bledi.weatherforecast.service;

import com.bledi.weatherforecast.model.AverageDataDto;
import java.util.List;

public interface WeatherForecastService {

  List<AverageDataDto> getAverageMaxMinTemp(String city);
}

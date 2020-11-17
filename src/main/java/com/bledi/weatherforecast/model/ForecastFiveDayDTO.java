package com.bledi.weatherforecast.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ForecastFiveDayDTO implements Serializable {

  private String cod;
  private String message;
  private Integer cnt;
  @JsonProperty("list")
  private List<ForecastFiveDayHourlyDataDTO> hourlyDataList;

}
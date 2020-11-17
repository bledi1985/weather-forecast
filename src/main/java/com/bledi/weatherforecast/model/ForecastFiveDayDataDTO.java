package com.bledi.weatherforecast.model;

import java.math.BigDecimal;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ForecastFiveDayDataDTO {

  private BigDecimal temp;
  private BigDecimal temp_min;
  private BigDecimal temp_max;
  private BigDecimal humidity;
  private BigDecimal temp_kf;

}

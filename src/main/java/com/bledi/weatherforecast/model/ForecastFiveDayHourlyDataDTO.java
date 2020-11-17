package com.bledi.weatherforecast.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ForecastFiveDayHourlyDataDTO {

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonProperty("dt_txt")
  private LocalDateTime dateTime;
  @JsonProperty("main")
  private ForecastFiveDayDataDTO data;

  @JsonIgnore
  public boolean isWorkingHours(int start, int end){
      return dateTime.getHour() >= start && dateTime.getHour() <= end;
  }

}

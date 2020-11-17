package com.bledi.weatherforecast.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Represents the wrapper for the data")
public class WrapperAverageData {

  @ApiModelProperty(value = "City of the research")
  private String city;

  @ApiModelProperty(value = "Data holding the result of the research")
  @JsonProperty("data")
  private List<AverageDataDto> averageDataDtoList;

  @ApiModelProperty(value = "The start of working hours")
  @JsonProperty("starting_working_hours")
  private int startWorkingHours;

  @ApiModelProperty(value = "The end of working hours")
  @JsonProperty("ending_working_hours")
  private int endWorkingHours;

}

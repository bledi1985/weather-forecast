package com.bledi.weatherforecast.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel(description = "Represents the model for the average data of a single day")
public class AverageDataDto implements Serializable {

  @ApiModelProperty(value = "This is the average of max temperature during working hours")
  @JsonProperty("avg_max_temp_wrk_hours")
  private BigDecimal averageMaxTempWorkingHours;

  @ApiModelProperty(value = "This is the average of min temperature during working hours")
  @JsonProperty("avg_min_temp_wrk_hours")
  private BigDecimal averageMinTempWorkingHours;

  @ApiModelProperty(value = "This is the average of max temperature outside working hours")
  @JsonProperty("avg_max_temp_not_wrk_hours")
  private BigDecimal averageMaxTempNotWorkingHours;

  @ApiModelProperty(value = "This is the average of min temperature outside working hours")
  @JsonProperty("avg_min_temp_not_wrk_hours")
  private BigDecimal averageMinTempNotWorkingHours;

  @ApiModelProperty(value = "This is the average of humidity during working hours")
  @JsonProperty("avg_humidity_wrk_hours")
  private BigDecimal averageHumidityWorkingHours;

  @ApiModelProperty(value = "This is the average of humidity outside working hours")
  @JsonProperty("avg_humidity_not_wrk_hours")
  private BigDecimal averageHumidityNotWorkingHours;


  @JsonIgnore
  private Integer totalWorkingHours;
  @JsonIgnore
  private Integer totalNotWorkingHours;
  @JsonIgnore
  private BigDecimal totalMaxTempWorkingHours;
  @JsonIgnore
  private BigDecimal totalMinTempWorkingHours;
  @JsonIgnore
  private BigDecimal totalMaxTempNotWorkingHours;
  @JsonIgnore
  private BigDecimal totalMinTempNotWorkingHours;
  @JsonIgnore
  private BigDecimal totalHumidityWorkingHours;
  @JsonIgnore
  private BigDecimal totalHumidityNotWorkingHours;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate date;

  public AverageDataDto() {
    this.totalMaxTempWorkingHours = BigDecimal.ZERO;
    this.totalMinTempWorkingHours = BigDecimal.ZERO;
    this.totalMaxTempNotWorkingHours = BigDecimal.ZERO;
    this.totalMinTempNotWorkingHours = BigDecimal.ZERO;
    this.totalHumidityWorkingHours = BigDecimal.ZERO;
    this.totalHumidityNotWorkingHours = BigDecimal.ZERO;
    this.totalWorkingHours = 0;
    this.totalNotWorkingHours = 0;
  }

  public void calculate() {
    if (this.totalWorkingHours > 0) {
      this.averageMaxTempWorkingHours = this.totalMaxTempWorkingHours.divide(
          BigDecimal.valueOf(this.totalWorkingHours), 2,
          RoundingMode.HALF_UP);
      this.averageMinTempWorkingHours = this.totalMinTempWorkingHours
          .divide(BigDecimal.valueOf(this.totalWorkingHours), 2, RoundingMode.HALF_UP);
      this.averageHumidityWorkingHours = this.totalHumidityWorkingHours
          .divide(BigDecimal.valueOf(this.totalWorkingHours), 2, RoundingMode.HALF_UP);
    }

    if (totalNotWorkingHours > 0) {
      this.averageMaxTempNotWorkingHours = this.totalMaxTempNotWorkingHours
          .divide(BigDecimal.valueOf(this.totalNotWorkingHours), 2, RoundingMode.HALF_UP);
      this.averageMinTempNotWorkingHours = this.totalMinTempNotWorkingHours
          .divide(BigDecimal.valueOf(this.totalNotWorkingHours), 2, RoundingMode.HALF_UP);
      this.averageHumidityNotWorkingHours = this.totalHumidityNotWorkingHours
          .divide(BigDecimal.valueOf(this.totalNotWorkingHours), 2, RoundingMode.HALF_UP);
    }
  }
}

package com.bledi.weatherforecast.model;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class AverageDataDtoTest {

  @Test
  public void testAverageValues(){
    AverageDataDto averageDataDto = new AverageDataDto();
    averageDataDto.setTotalWorkingHours(10);
    averageDataDto.setTotalNotWorkingHours(10);
    averageDataDto.setTotalMaxTempWorkingHours(BigDecimal.valueOf(10));
    averageDataDto.setTotalMaxTempNotWorkingHours(BigDecimal.valueOf(10));
    averageDataDto.calculate();
    Assert.assertTrue(averageDataDto.getAverageMaxTempWorkingHours().compareTo(BigDecimal.ONE)==0);
    Assert.assertTrue(averageDataDto.getAverageMaxTempNotWorkingHours().compareTo(BigDecimal.ONE)==0);
  }

  @Test
  public void testAverageValuesWithNoData(){
    AverageDataDto averageDataDto = new AverageDataDto();
    averageDataDto.calculate();
    Assert.assertNull(averageDataDto.getAverageMaxTempWorkingHours());
    Assert.assertNull(averageDataDto.getAverageMaxTempNotWorkingHours());
    Assert.assertNull(averageDataDto.getAverageMinTempWorkingHours());
    Assert.assertNull(averageDataDto.getAverageMinTempNotWorkingHours());
    Assert.assertNull(averageDataDto.getAverageMinTempNotWorkingHours());
    Assert.assertNull(averageDataDto.getAverageHumidityWorkingHours());
    Assert.assertNull(averageDataDto.getAverageHumidityNotWorkingHours());
  }

}
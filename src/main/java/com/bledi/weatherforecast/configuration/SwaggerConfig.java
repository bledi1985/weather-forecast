package com.bledi.weatherforecast.configuration;

import static springfox.documentation.builders.PathSelectors.regex;

import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Value(value = "${swagger.enabled}")
  Boolean swaggerEnabled;
  private final ServletContext servletContext;

  public SwaggerConfig(ServletContext servletContext) {
    this.servletContext = servletContext;
  }

  @Bean
  public Docket swaggerConfigurationAPI10() {

    return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
        .groupName("weather-forcast-api-1.0")
        .pathProvider(new RelativePathProvider(servletContext) {
          @Override
          public String getApplicationBasePath() {
            return "/api";
          }})
        .enable(swaggerEnabled).select()
        .apis(RequestHandlerSelectors.basePackage("com.bledi.weatherforecast.rest"))
        .paths(regex("/weather-forecast/v1.0.*"))
        .build();
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("Weather Forecast API")
        .description("Weather bulletin that will be used by employees to check the weather conditions in the cities where other team mates work. ")
        .contact(new Contact("Bledi Nukaj", "https://www.linkedin.com/in/bledinukaj/", "bledi.nukaj@gmail"))
        .version("1.0")
        .build();
  }
}

package com.terminus.sample.controllers;


import com.terminus.sample.dto.WeatherDTO;
import com.terminus.sample.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static com.terminus.sample.controllers.ApiConstants.CURRENT_WEATHER_URI;
import static com.terminus.sample.controllers.ApiConstants.QUERY_PARAM_LOCATION;


@RestController

@Validated

public class CurrentWeatherController {
    private WeatherService weatherService;


    @GetMapping(ApiConstants.CONTROLLER_BASE_URI + CURRENT_WEATHER_URI)
    public ResponseEntity<WeatherDTO> getCurrentWeatherByCity(@RequestParam(QUERY_PARAM_LOCATION)
                                                              @Pattern(regexp = "^[a-zA-Z ]+\\,*[ a-zA-Z]{0,3}$")
                                                              @NotNull String location) {
        return ResponseEntity.ok()
                .body(weatherService
                        .getCurrentWeatherByCity(location)
                        .orElseThrow(RuntimeException::new));
    }


    @Autowired
    @Lazy
    public void setWeatherService(WeatherService weatherService) {
        this.weatherService = weatherService;
    }


}

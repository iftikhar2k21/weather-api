package com.terminus.sample.controllers;

import com.terminus.sample.dto.WeatherHistoryDTO;
import com.terminus.sample.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static com.terminus.sample.controllers.ApiConstants.WEATHER_HISTORY_URI;

@RestController
@Validated
public class WeatherHistoryController {
    private WeatherService weatherService;

    @GetMapping(ApiConstants.CONTROLLER_BASE_URI + WEATHER_HISTORY_URI)
    public ResponseEntity<WeatherHistoryDTO> getWeatherHistoryByLocation(@RequestParam(ApiConstants.QUERY_PARAM_LOCATION)
                                                                         @Pattern(regexp = "^[a-zA-Z ]+\\,*[ a-zA-Z]{0,3}$")
                                                                         @NotNull String location
    ) {

        WeatherHistoryDTO apiResult =  weatherService.getWeatherHistoryByLocation(location)
                .orElseThrow(RuntimeException::new);

        return ResponseEntity.ok().body(apiResult);
    }

    public WeatherHistoryController(WeatherService weatherService){
        this.weatherService = weatherService;
    }

}

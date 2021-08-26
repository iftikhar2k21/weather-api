package com.terminus.sample.service;

import com.terminus.sample.dto.WeatherDTO;
import com.terminus.sample.dto.WeatherHistoryDTO;

import java.util.Optional;

public interface WeatherService {
    Optional<WeatherHistoryDTO> getWeatherHistoryByLocation(String location);
    Optional<WeatherDTO> getCurrentWeatherByCity(String location);

}

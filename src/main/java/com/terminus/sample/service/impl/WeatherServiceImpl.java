package com.terminus.sample.service.impl;

import com.terminus.sample.dto.WeatherDTO;
import com.terminus.sample.dto.WeatherHistoryDTO;
import com.terminus.sample.service.WeatherService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WeatherServiceImpl implements WeatherService {
    @Override
    public Optional<WeatherHistoryDTO> getWeatherHistoryByLocation(String location) {
        return Optional.empty();
    }

    @Override
    public Optional<WeatherDTO> getCurrentWeatherByCity(String location) {
        return Optional.empty();
    }
}

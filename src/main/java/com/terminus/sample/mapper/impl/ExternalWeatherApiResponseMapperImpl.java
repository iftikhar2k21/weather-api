package com.terminus.sample.mapper.impl;

import com.terminus.sample.dto.WeatherDTO;
import com.terminus.sample.dto.WeatherHistoryDTO;
import com.terminus.sample.mapper.ExternalWeatherApiResponseMapper;

import java.util.Optional;

public class ExternalWeatherApiResponseMapperImpl implements ExternalWeatherApiResponseMapper {
    @Override
    public Optional<WeatherDTO> mapJsonToWeatherDTO(String json) {
        return Optional.empty();
    }

    @Override
    public Optional<WeatherHistoryDTO> mapJsonToWeatherHistoryDTO(String weatherInfoJSON) {
        return Optional.empty();
    }
}

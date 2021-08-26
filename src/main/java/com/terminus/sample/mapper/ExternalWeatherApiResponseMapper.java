package com.terminus.sample.mapper;


import com.terminus.sample.dto.WeatherDTO;
import com.terminus.sample.dto.WeatherHistoryDTO;

import java.util.Optional;

public interface ExternalWeatherApiResponseMapper {
    Optional<WeatherDTO> mapJsonToWeatherDTO(String json);
    Optional<WeatherHistoryDTO> mapJsonToWeatherHistoryDTO(String weatherInfoJSON);
}

package com.terminus.sample.service.impl;

import com.terminus.sample.dto.GeoCodeInfoDTO;
import com.terminus.sample.dto.WeatherDTO;
import com.terminus.sample.dto.WeatherHistoryDTO;
import com.terminus.sample.exceptions.InvalidCityNameOrCountryCodeException;
import com.terminus.sample.mapper.ExternalWeatherApiResponseMapper;
import com.terminus.sample.mapper.LocationAPIResponseMapper;
import com.terminus.sample.remote.LocationProvider;
import com.terminus.sample.remote.WeatherInfoProvider;
import com.terminus.sample.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WeatherServiceImpl implements WeatherService {
    private LocationProvider locationProvider;
    private WeatherInfoProvider weatherInfoProvider;
    private LocationAPIResponseMapper geoCodingResponseMapper;
    private ExternalWeatherApiResponseMapper externalWeatherApiResponseMapper;

    @Override
    public Optional<WeatherDTO> getCurrentWeatherByCity(String location) {

        var geoCodeInfoJSON = locationProvider.getGeocodeInfoByLocation(getCityNameAndCountryCode(location));
        var geoCodeInfoDTO =
                geoCodingResponseMapper.
                        mapJsonToGeoCodingDTO(geoCodeInfoJSON)
                        .orElseThrow(() -> new InvalidCityNameOrCountryCodeException("Invalid City Name or Country Code "));

        var weatherInfoJSON = weatherInfoProvider.getCurrentWeatherInfo(geoCodeInfoDTO.getLatitude(), geoCodeInfoDTO.getLongitude());
        return externalWeatherApiResponseMapper.mapJsonToWeatherDTO(weatherInfoJSON);
    }


    @Override
    public Optional<WeatherHistoryDTO> getWeatherHistoryByLocation(String location) {
        var geoCodeInfoJSON = locationProvider.getGeocodeInfoByLocation(getCityNameAndCountryCode(location));
        var geoCodeInfoDTO = geoCodingResponseMapper.mapJsonToGeoCodingDTO(geoCodeInfoJSON)
                .orElseThrow(() -> new InvalidCityNameOrCountryCodeException
                        ("Invalid City name or Country Code"));
        var weatherInfoJSON = weatherInfoProvider.getHistoricalWeatherInfo(geoCodeInfoDTO.getLatitude(), geoCodeInfoDTO.getLongitude());
        return externalWeatherApiResponseMapper.mapJsonToWeatherHistoryDTO(weatherInfoJSON);
    }


    @Autowired
    @Lazy
    public void setCoordinatesProvider(LocationProvider locationProvider) {
        this.locationProvider = locationProvider;
    }

    @Autowired
    @Lazy
    public void setGeoCodingResponseMapper(LocationAPIResponseMapper geoCodingResponseMapper) {
        this.geoCodingResponseMapper = geoCodingResponseMapper;
    }

    @Autowired
    @Lazy
    public void setWeatherInfoProvider(WeatherInfoProvider weatherInfoProvider) {
        this.weatherInfoProvider = weatherInfoProvider;
    }

    @Autowired
    @Lazy
    public void setWeatherResponseMapper(ExternalWeatherApiResponseMapper ExternalWeatherApiResponseMapper) {
        this.externalWeatherApiResponseMapper = ExternalWeatherApiResponseMapper;
    }

    private GeoCodeInfoDTO getCityNameAndCountryCode(String location) {
        var locationSplit = location.split(",");
        var geoCodeInfoDTO = new GeoCodeInfoDTO();
        geoCodeInfoDTO.setCityName(locationSplit[0].trim());
        if (locationSplit.length > 1) {
            geoCodeInfoDTO.setCountryCode(locationSplit[1].trim());
        }
        return geoCodeInfoDTO;
    }
}

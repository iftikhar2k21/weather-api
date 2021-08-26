package com.terminus.sample.mapper.impl;

import com.terminus.sample.dto.GeoCodeInfoDTO;
import com.terminus.sample.mapper.LocationAPIResponseMapper;

import java.util.Optional;

public class LocationApiResponseMapperImpl implements LocationAPIResponseMapper {
    @Override
    public Optional<GeoCodeInfoDTO> mapJsonToGeoCodingDTO(String json) {
        return Optional.empty();
    }
}

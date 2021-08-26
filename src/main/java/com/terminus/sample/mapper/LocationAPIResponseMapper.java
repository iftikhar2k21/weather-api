package com.terminus.sample.mapper;

import com.terminus.sample.dto.GeoCodeInfoDTO;

import java.util.Optional;

public interface LocationAPIResponseMapper {

    Optional<GeoCodeInfoDTO> mapJsonToGeoCodingDTO(String json);
}

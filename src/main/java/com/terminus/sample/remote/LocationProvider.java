package com.terminus.sample.remote;

import com.terminus.sample.dto.GeoCodeInfoDTO;

public interface LocationProvider {

    String getGeocodeInfoByLocation(GeoCodeInfoDTO location);

}

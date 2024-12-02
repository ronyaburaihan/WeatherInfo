package com.englesoft.weatherinfo.data.mapper

import com.englesoft.weatherinfo.data.model.Zila
import com.englesoft.weatherinfo.domain.model.ZilaInfo

fun Zila.toZilaInfo(): ZilaInfo {
    return ZilaInfo(
        id = id,
        name = name,
        lat = coord.lat,
        lon = coord.lon
    )
}
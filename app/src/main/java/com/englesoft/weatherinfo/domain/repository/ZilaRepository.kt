package com.englesoft.weatherinfo.domain.repository

import com.englesoft.weatherinfo.domain.model.ZilaInfo
import com.englesoft.weatherinfo.util.Result

interface ZilaRepository {
    suspend fun getZilas(): Result<List<ZilaInfo>>
}
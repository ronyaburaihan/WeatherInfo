package com.englesoft.weatherinfo.data.repository

import com.englesoft.weatherinfo.data.mapper.toZilaInfo
import com.englesoft.weatherinfo.data.util.ZilaAssetsReader
import com.englesoft.weatherinfo.domain.model.ZilaInfo
import com.englesoft.weatherinfo.domain.repository.ZilaRepository
import com.englesoft.weatherinfo.util.Result
import javax.inject.Inject

class ZilaRepositoryImpl @Inject constructor(
    private val zilaAssetsReader: ZilaAssetsReader
) : ZilaRepository {

    override suspend fun getZilas(): Result<List<ZilaInfo>> {
        return try {
            val cityList = zilaAssetsReader.readZilaListFromAssets().map {
                it.toZilaInfo()
            }
            Result.Success(cityList)
        } catch (exception: Exception) {
            exception.printStackTrace()
            Result.Error(
                exception.localizedMessage ?: "An error occurred while reading the zila list."
            )
        }
    }
}
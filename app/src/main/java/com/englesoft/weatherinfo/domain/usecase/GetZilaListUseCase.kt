package com.englesoft.weatherinfo.domain.usecase

import com.englesoft.weatherinfo.data.model.Zila
import com.englesoft.weatherinfo.domain.model.ZilaInfo
import com.englesoft.weatherinfo.domain.repository.ZilaRepository
import com.englesoft.weatherinfo.util.Result
import javax.inject.Inject

class GetZilaListUseCase @Inject constructor(
    private val zilaRepository: ZilaRepository
) {
    suspend fun execute(): Result<List<ZilaInfo>> {
        return zilaRepository.getZilas()
    }
}
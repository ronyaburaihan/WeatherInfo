package com.englesoft.weatherinfo.presentation.feature.search

import com.englesoft.weatherinfo.domain.model.ZilaInfo

data class SearchScreenState(
    val zilaList: List<ZilaInfo> = listOf(),
    val filteredZilaList: List<ZilaInfo> = listOf(),
    val isLoading: Boolean = false,
    val error: String? = null
)
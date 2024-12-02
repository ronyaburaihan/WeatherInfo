package com.englesoft.weatherinfo.presentation.feature.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.englesoft.weatherinfo.domain.usecase.GetZilaListUseCase
import com.englesoft.weatherinfo.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getZilaListUseCase: GetZilaListUseCase
) : ViewModel() {

    var state by mutableStateOf(SearchScreenState())
        private set

    var searchQuery by mutableStateOf("")
        private set

    init {
        loadZilas()
    }

    private fun loadZilas() {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )

            val result = getZilaListUseCase.execute()

            state = when (result) {
                is Result.Success -> state.copy(
                    zilaList = result.data ?: emptyList(),
                    filteredZilaList = result.data ?: emptyList(),
                    isLoading = false,
                    error = null
                )
                else -> state.copy(
                    zilaList = emptyList(),
                    filteredZilaList = emptyList(),
                    isLoading = false,
                    error = result.message ?: "An unexpected error occurred."
                )
            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        searchQuery = query

        state = state.copy(
            filteredZilaList = if (query.isBlank()) {
                state.zilaList
            } else {
                state.zilaList.filter {
                    it.name.contains(query, ignoreCase = true)
                }
            }
        )
    }
}

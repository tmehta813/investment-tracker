package org.addepar.investmenttracker.feature.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.addepar.investmenttracker.feature.home.data.HomeRepo
import org.addepar.investmenttracker.core.data.utils.Either
import org.addepar.investmenttracker.feature.common.UiState
import org.addepar.investmenttracker.feature.common.models.Investment
import javax.inject.Inject

sealed interface HomePageEvent {
    data object LoadData : HomePageEvent
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepo,
) : ViewModel() {

    private val _state: MutableStateFlow<UiState<List<Investment>>> = MutableStateFlow(UiState.Loading)
    val state: StateFlow<UiState<List<Investment>>> = _state

    init {
        handleEvent(HomePageEvent.LoadData)
    }

    fun handleEvent(event: HomePageEvent) {
        when (event) {
            HomePageEvent.LoadData -> loadInvestments()
        }
    }

    private fun loadInvestments() {
        viewModelScope.launch {
            _state.update { UiState.Loading }
            delay(2000)
            when (val result = repository.getInvestments()) {
                is Either.Left -> _state.update { UiState.Error(result.value.toString()) }
                is Either.Right -> _state.update { UiState.Success(result.value) }
            }
        }
    }


}
package org.addepar.investmenttracker.feature.details.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.addepar.investmenttracker.core.data.utils.Either
import org.addepar.investmenttracker.feature.common.UiState
import org.addepar.investmenttracker.feature.details.data.GetInvestmentByIdFailure
import org.addepar.investmenttracker.feature.details.data.InvestmentDetailsRepo
import org.addepar.investmenttracker.feature.common.models.Investment
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: InvestmentDetailsRepo,
) : ViewModel() {

    private val id: Int = savedStateHandle["id"] ?: INVALID_ID

    private val _state: MutableStateFlow<UiState<Investment>> = MutableStateFlow(
        value = if (id == INVALID_ID) UiState.Error("Invalid Id") else UiState.Loading
    )
    val state: StateFlow<UiState<Investment>> = _state

    init {
        handleEvent(DetailsPageEvent.LoadInvestmentDetails)
    }

    fun handleEvent(event: DetailsPageEvent) {
        when (event) {
            DetailsPageEvent.LoadInvestmentDetails -> loadInvestmentDetails()
        }
    }

    private fun loadInvestmentDetails() {
        viewModelScope.launch {
            if (id == INVALID_ID) {
                _state.update { UiState.Error("Invalid Id") }
                return@launch
            }

            _state.update { UiState.Loading }
            when (val result = repository.getInvestmentById(id)) {
                is Either.Left -> {
                    val nextState = when (val failure = result.value) {
                        GetInvestmentByIdFailure.InvestmentNotFound -> UiState.Error("Invalid Id")
                        is GetInvestmentByIdFailure.Error -> UiState.Error(
                            failure.cause.message ?: "Something went wrong"
                        )
                    }
                    _state.update { nextState }
                }

                is Either.Right -> {
                    _state.update { UiState.Success(result.value) }
                }
            }
        }
    }

    companion object {
        private const val INVALID_ID = -1
    }

}
package org.addepar.investmenttracker.feature.details

import androidx.lifecycle.SavedStateHandle
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.addepar.investmenttracker.core.data.utils.Either
import org.addepar.investmenttracker.feature.common.UiState
import org.addepar.investmenttracker.feature.details.presentation.DetailsViewModel
import org.addepar.investmenttracker.feature.common.models.Investment
import org.addepar.investmenttracker.feature.details.data.GetInvestmentByIdFailure
import org.addepar.investmenttracker.feature.details.data.InvestmentDetailsRepo
import org.addepar.investmenttracker.rules.MainDispatcherRule
import org.junit.Rule
import org.junit.Test

class DetailsViewModelTest {

    val testDispatcher = StandardTestDispatcher()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule(testDispatcher)

    val sampleInvestment = Investment(
        1,
        "Netflix Inc",
        "NFLX",
        "8900000000",
        "5000000000",
        "A video production company, streaming giant, and maker of movies."
    )

    @Test
    fun `Initial state should be Loading`() = runTest {
        val savedStateHandle = SavedStateHandle(mapOf("id" to 1))

        val repository = mockk<InvestmentDetailsRepo>()
        coEvery { repository.getInvestmentById(1) } returns Either.Right(sampleInvestment)

        val viewModel = DetailsViewModel(savedStateHandle, repository)

        val initialState = viewModel.state.value
        assertTrue(initialState is UiState.Loading)
    }

    @Test
    fun `Initial state should be InvalidId if given id is invalid`() = runTest {
        val repository = mockk<InvestmentDetailsRepo>()
        coEvery { repository.getInvestmentById(1) } returns Either.Right(sampleInvestment)

        val viewModel = DetailsViewModel(SavedStateHandle(), repository)

        val initialState = viewModel.state.value
        assertTrue(initialState is UiState.Error)
    }

    @Test
    fun `loadInvestmentDetails should update state to Success when repository returns investment`() =
        runTest {
            val savedStateHandle = SavedStateHandle(mapOf("id" to 1))

            val repository = mockk<InvestmentDetailsRepo>()
            coEvery { repository.getInvestmentById(1) } returns Either.Right(sampleInvestment)

            val viewModel = DetailsViewModel(savedStateHandle, repository)

            testDispatcher.scheduler.advanceUntilIdle()

            val state = viewModel.state.value
            assertTrue(state is UiState.Success)
            if (state is UiState.Success) {
                assertEquals(sampleInvestment, state.data)
            }
        }

    @Test
    fun `loadInvestmentDetails should update state to InvalidId when repository returns InvestmentNotFound`() =
        runTest {
            val savedStateHandle = SavedStateHandle(mapOf("id" to 1))

            val repository = mockk<InvestmentDetailsRepo>()
            coEvery { repository.getInvestmentById(1) } returns Either.Left(GetInvestmentByIdFailure.InvestmentNotFound)

            val viewModel = DetailsViewModel(savedStateHandle, repository)

            testDispatcher.scheduler.advanceUntilIdle()

            val state = viewModel.state.value
            assertTrue(state is UiState.Error)
        }

    @Test
    fun `loadInvestmentDetails should update state to Error when repository returns Error`() =
        runTest {
            val savedStateHandle = SavedStateHandle(mapOf("id" to 1))

            val repository = mockk<InvestmentDetailsRepo>()
            coEvery { repository.getInvestmentById(1) } returns Either.Left(
                GetInvestmentByIdFailure.Error(
                    Exception("Error Occurred")
                )
            )

            val viewModel = DetailsViewModel(savedStateHandle, repository)

            testDispatcher.scheduler.advanceUntilIdle()

            val state = viewModel.state.value
            assertTrue(state is UiState.Error)
        }
}
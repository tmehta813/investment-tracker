package org.addepar.investmenttracker.feature.home
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.addepar.investmenttracker.feature.home.data.GetInvestmentsFailure
import org.addepar.investmenttracker.feature.home.data.HomeRepo
import org.addepar.investmenttracker.core.data.utils.Either
import org.addepar.investmenttracker.feature.common.UiState
import org.addepar.investmenttracker.feature.home.presentation.HomePageEvent
import org.addepar.investmenttracker.feature.home.presentation.HomeViewModel
import org.addepar.investmenttracker.feature.common.models.Investment
import org.addepar.investmenttracker.rules.MainDispatcherRule
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule(testDispatcher)

    @Test
    fun `Initial state should be Loading Test`() = runTest {
        val repository = mockk<HomeRepo>(relaxed = true)
        val viewModel = HomeViewModel(repository)
        val state = viewModel.state.value
        assertTrue(state is UiState.Loading)
    }

    @Test
    fun `loadInvestments update state to Error Test`() = runTest {
        val repository = mockk<HomeRepo>()
        coEvery { repository.getInvestments() } returns Either.Left(
            GetInvestmentsFailure.Error(
                Exception("Error fetching investments")
            )
        )

        val homeViewModel = HomeViewModel(repository)
        homeViewModel.handleEvent(HomePageEvent.LoadData)

        testDispatcher.scheduler.advanceUntilIdle()

        val state = homeViewModel.state.value
        assertTrue(state is UiState.Error)
    }

    @Test
    fun `loadInvestments update state to Success Test`() =
        runTest {
            val investments = listOf(
                Investment(
                    1,
                    "Netflix Inc",
                    "NFLX",
                    "8900000000",
                    "5000000000",
                    "A video production company, streaming giant, and maker of movies."
                )
            )

            val repository = mockk<HomeRepo>()
            coEvery { repository.getInvestments() } returns Either.Right(investments)

            val viewModel = HomeViewModel(repository)
            viewModel.handleEvent(HomePageEvent.LoadData)

            testDispatcher.scheduler.advanceUntilIdle()

            val state = viewModel.state.value
            assertTrue(state is UiState.Success)

            if (state is UiState.Success) {
                assertEquals(investments, state.data)
            }
        }
}
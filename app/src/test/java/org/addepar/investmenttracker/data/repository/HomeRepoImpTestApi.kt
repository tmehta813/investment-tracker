package org.addepar.investmenttracker.data.repository

import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.addepar.investmenttracker.core.data.api.InvestmentsApi
import org.addepar.investmenttracker.core.data.models.InvestmentDto
import org.addepar.investmenttracker.core.data.models.InvestmentsApiResponse
import org.addepar.investmenttracker.core.data.utils.Either
import org.addepar.investmenttracker.feature.details.data.GetInvestmentByIdFailure
import org.addepar.investmenttracker.feature.details.data.InvestmentDetailsRepo
import org.addepar.investmenttracker.feature.details.data.InvestmentDetailsRepoImp
import org.addepar.investmenttracker.feature.home.data.GetInvestmentsFailure
import org.addepar.investmenttracker.feature.home.data.HomeRepo
import org.addepar.investmenttracker.feature.home.data.HomeRepoImp
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeRepoImpTestApi {

    private lateinit var investmentsApi: InvestmentsApi
    private lateinit var investmentDetailsRepo: InvestmentDetailsRepo
    private lateinit var homeRepo: HomeRepo
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        investmentsApi = mockk()
        investmentDetailsRepo = InvestmentDetailsRepoImp(investmentsApi)
        homeRepo = HomeRepoImp(investmentsApi)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getInvestments should return Right with investments on success`() = runTest {
        val investmentDtos = listOf(
            InvestmentDto(
                1,
                "Investment XYZ",
                "INV",
                "5000.0",
                "2500.0",
                "Details of Investment XYZ"
            ),
            InvestmentDto(
                2,
                "Investment ZYX",
                "INV2",
                "2000.0",
                "1000.0",
                "Details of Investment ZYX"
            )
        )
        coEvery { investmentsApi.getInvestments() } returns InvestmentsApiResponse(investmentDtos)

        val result = homeRepo.getInvestments()

        assert(result is Either.Right)
        if (result is Either.Right) {
            assertEquals(2, result.value.size)
            assertEquals("Investment XYZ", result.value[0].name)
            assertEquals("Investment ZYX", result.value[1].name)
        }
    }

    @Test
    fun `getInvestments should return Left with Error on exception`() = runTest {
        val exception = Exception("Network error")
        coEvery { investmentsApi.getInvestments() } throws exception

        val result = homeRepo.getInvestments()

        assert(result is Either.Left)
        if (result is Either.Left) {
            assert(result.value is GetInvestmentsFailure.Error)
            assertEquals(exception, (result.value as GetInvestmentsFailure.Error).cause)
        }
    }

    @Test
    fun `getInvestmentById should return Right with investment on success`() = runTest {
        val investmentDto = InvestmentDto(
            1,
            "Investment XYZ",
            "INV",
            "1000.0",
            "500.0",
            "Details of Investment XYZ"
        )
        coEvery { investmentsApi.getInvestmentById(1) } returns investmentDto

        val result = investmentDetailsRepo.getInvestmentById(1)

        assert(result is Either.Right)
        if (result is Either.Right) {
            assertEquals("Investment XYZ", result.value.name)
        }
    }

    @Test
    fun `getInvestmentById should return Left with InvestmentNotFound when investment is null`() =
        runTest {
            coEvery { investmentsApi.getInvestmentById(1) } returns null

            val result = investmentDetailsRepo.getInvestmentById(1)

            assert(result is Either.Left)
            if (result is Either.Left) {
                assert(result.value is GetInvestmentByIdFailure.InvestmentNotFound)
            }
        }

    @Test
    fun `getInvestmentById should return Left with Error on exception`() = runTest {
        val exception = Exception("Network error")
        coEvery { investmentsApi.getInvestmentById(1) } throws exception

        val result = investmentDetailsRepo.getInvestmentById(1)

        assert(result is Either.Left)
        if (result is Either.Left) {
            assert(result.value is GetInvestmentByIdFailure.Error)
            assertEquals(exception, (result.value as GetInvestmentByIdFailure.Error).cause)
        }
    }

}
package org.addepar.investmenttracker.core.data.sources.fake

import org.addepar.investmenttracker.core.data.api.InvestmentsApi
import org.addepar.investmenttracker.core.data.models.InvestmentDto
import org.addepar.investmenttracker.core.data.models.InvestmentsApiResponse
import javax.inject.Inject

class FakeInvestmentsSource @Inject constructor() : InvestmentsApi {

    private val investments = listOf(
        InvestmentDto(
            1,
            "Netflix Inc",
            "NFLX",
            "8900000000",
            "5000000000",
            "A video production company, streaming giant, and maker of movies."
        ),
        InvestmentDto(
            2,
            "Amazon",
            "AMZN",
            "1100000000",
            "2000000000",
            "A company that needs no introduction."
        ),
        InvestmentDto(
            3,
            "Watch Portfolio",
            null,
            "8500000",
            "2000000",
            "A private watch portfolio that is not publicly traded."
        ),
        InvestmentDto(
            4,
            "Fidelity Bitcoin Fund",
            "FBTC",
            "500000000",
            "11000",
            "A well known cryptocurrency fund."
        ),
        InvestmentDto(
            5,
            "Ethereum",
            null,
            "15000000",
            "25000000",
            "Another well known cryptocurrency that is owned on-chain"
        ),
        InvestmentDto(
            6,
            "Private Equity Fund 15",
            null,
            "600000",
            "200000",
            "An investment in a private equity fund."
        )
    )

    override suspend fun getInvestments(): InvestmentsApiResponse {
        return InvestmentsApiResponse(investments)
    }

    override suspend fun getInvestmentById(id: Int): InvestmentDto? {
        return investments.find { it.id == id }
    }
}
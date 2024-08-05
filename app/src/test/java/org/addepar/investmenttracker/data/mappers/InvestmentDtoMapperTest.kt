package org.addepar.investmenttracker.data.mappers

import junit.framework.TestCase.assertEquals
import org.addepar.investmenttracker.core.data.models.InvestmentDto
import org.addepar.investmenttracker.core.data.models.mapToDomainModels
import org.addepar.investmenttracker.core.data.models.toInvestment
import org.junit.Test

class InvestmentDtoMapperTest {

    @Test
    fun `InvestmentDto to Investment`() {
        val investmentDto = InvestmentDto(
            id = 1,
            name = "Investment XYZ",
            ticker = "INV",
            value = "1000.0",
            principal = "500.0",
            details = "Details of Investment XYZ"
        )

        val investment = investmentDto.toInvestment()

        assertEquals(investmentDto.id, investment.id)
        assertEquals(investmentDto.name, investment.name)
        assertEquals(investmentDto.ticker, investment.ticker)
        assertEquals(investmentDto.value, investment.value)
        assertEquals(investmentDto.principal, investment.principal)
        assertEquals(investmentDto.details, investment.details)
    }

    @Test
    fun `List of InvestmentDto to List of Investment`() {
        val investmentDtos = listOf(
            InvestmentDto(
                id = 1,
                name = "Investment XYZ",
                ticker = "INV",
                value = "840.0",
                principal = "250.0",
                details = "Details of Investment A"
            ),
            InvestmentDto(
                id = 2,
                name = "Investment ZYX",
                ticker = "INV2",
                value = "5000.40",
                principal = "2000.30",
                details = "Details of Investment B"
            )
        )

        val investments = investmentDtos.mapToDomainModels()

        assertEquals(investmentDtos.size, investments.size)

        for (i in investmentDtos.indices) {
            assertEquals(investmentDtos[i].id, investments[i].id)
            assertEquals(investmentDtos[i].name, investments[i].name)
            assertEquals(investmentDtos[i].ticker, investments[i].ticker)
            assertEquals(investmentDtos[i].value, investments[i].value)
            assertEquals(investmentDtos[i].principal, investments[i].principal)
            assertEquals(investmentDtos[i].details, investments[i].details)
        }
    }

}
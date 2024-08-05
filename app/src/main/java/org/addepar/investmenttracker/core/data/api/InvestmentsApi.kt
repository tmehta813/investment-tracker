package org.addepar.investmenttracker.core.data.api

import org.addepar.investmenttracker.core.data.models.InvestmentDto
import org.addepar.investmenttracker.core.data.models.InvestmentsApiResponse

interface InvestmentsApi {
    suspend fun getInvestments(): InvestmentsApiResponse
    suspend fun getInvestmentById(id: Int): InvestmentDto?
}

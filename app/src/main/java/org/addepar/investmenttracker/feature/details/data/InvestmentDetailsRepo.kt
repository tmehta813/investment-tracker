package org.addepar.investmenttracker.feature.details.data

import org.addepar.investmenttracker.core.data.utils.Either
import org.addepar.investmenttracker.feature.common.models.Investment

interface InvestmentDetailsRepo {
    suspend fun getInvestmentById(
        id: Int
    ): Either<GetInvestmentByIdFailure, Investment>
}
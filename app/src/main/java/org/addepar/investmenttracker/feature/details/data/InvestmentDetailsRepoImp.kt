package org.addepar.investmenttracker.feature.details.data

import org.addepar.investmenttracker.core.data.api.InvestmentsApi
import org.addepar.investmenttracker.core.data.models.toInvestment
import org.addepar.investmenttracker.core.data.utils.Either
import org.addepar.investmenttracker.core.di.FakeInvestmentApi
import org.addepar.investmenttracker.feature.common.models.Investment
import javax.inject.Inject

class InvestmentDetailsRepoImp @Inject constructor(
    @FakeInvestmentApi private val investmentsApi: InvestmentsApi
)  : InvestmentDetailsRepo {

    override suspend fun getInvestmentById(id: Int): Either<GetInvestmentByIdFailure, Investment> {
        return try {
            val investment = investmentsApi.getInvestmentById(id)
            when {
                investment == null -> Either.Left(GetInvestmentByIdFailure.InvestmentNotFound)
                else -> Either.Right(investment.toInvestment())
            }
        } catch (e: Exception) {
            Either.Left(GetInvestmentByIdFailure.Error(e))
        }
    }
}
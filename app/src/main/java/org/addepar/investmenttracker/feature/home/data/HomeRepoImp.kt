package org.addepar.investmenttracker.feature.home.data

import org.addepar.investmenttracker.core.data.api.InvestmentsApi
import org.addepar.investmenttracker.core.data.models.mapToDomainModels
import org.addepar.investmenttracker.core.data.utils.Either
import org.addepar.investmenttracker.core.di.FakeInvestmentApi
import org.addepar.investmenttracker.feature.common.models.Investment
import javax.inject.Inject

class HomeRepoImp @Inject constructor(
    @FakeInvestmentApi private val investmentsApi: InvestmentsApi
) : HomeRepo {

    override suspend fun getInvestments(): Either<GetInvestmentsFailure, List<Investment>> {
        return try {
            val investments = investmentsApi.getInvestments().investments
            if ((Math.random() % 2).toInt() == 0) {
                return Either.Right(investments.mapToDomainModels())
            }
            Either.Left(GetInvestmentsFailure.Error(Throwable("Something went wrong")))
        } catch (e: Exception) {
            Either.Left(GetInvestmentsFailure.Error(e))
        }
    }

}
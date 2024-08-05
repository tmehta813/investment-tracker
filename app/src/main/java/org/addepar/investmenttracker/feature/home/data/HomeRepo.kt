package org.addepar.investmenttracker.feature.home.data

import org.addepar.investmenttracker.core.data.utils.Either
import org.addepar.investmenttracker.feature.common.models.Investment

interface HomeRepo {
    suspend fun getInvestments(): Either<GetInvestmentsFailure, List<Investment>>
}
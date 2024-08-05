package org.addepar.investmenttracker.feature.details.data

sealed interface GetInvestmentByIdFailure {
    data object InvestmentNotFound : GetInvestmentByIdFailure
    data class Error(
        val cause: Throwable
    ) : GetInvestmentByIdFailure
}
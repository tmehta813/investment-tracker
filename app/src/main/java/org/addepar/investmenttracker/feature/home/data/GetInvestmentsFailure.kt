package org.addepar.investmenttracker.feature.home.data

sealed interface GetInvestmentsFailure {
    data class Error(
        val cause: Throwable
    ) : GetInvestmentsFailure
}
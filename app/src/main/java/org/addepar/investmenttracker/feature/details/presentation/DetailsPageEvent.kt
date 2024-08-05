package org.addepar.investmenttracker.feature.details.presentation

sealed interface DetailsPageEvent {
    data object LoadInvestmentDetails : DetailsPageEvent
}
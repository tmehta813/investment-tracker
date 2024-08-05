package org.addepar.investmenttracker.core.data.models

import org.addepar.investmenttracker.feature.common.models.Investment

data class InvestmentDto(
    val id: Int,
    val name: String,
    val ticker: String?,
    val value: String,
    val principal: String,
    val details: String
)

fun InvestmentDto.toInvestment(): Investment = Investment(
    id = id,
    name = name,
    ticker = ticker,
    value = value,
    principal = principal,
    details = details
)

fun List<InvestmentDto>.mapToDomainModels() = map { it.toInvestment() }

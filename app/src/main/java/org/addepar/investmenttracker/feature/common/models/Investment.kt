package org.addepar.investmenttracker.feature.common.models

import androidx.compose.runtime.Immutable

/**
 * Represents a single investment.
 */
@Immutable
data class Investment(
    val id: Int,
    val name: String,
    val ticker: String?,
    val value: String,
    val principal: String,
    val details: String
)

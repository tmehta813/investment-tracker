package org.addepar.investmenttracker.feature.home.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.addepar.investmenttracker.feature.common.models.Investment
import org.addepar.investmenttracker.feature.home.presentation.InvestmentCard

@Composable
internal fun InvestmentsList(
    investments: List<Investment>,
    onInvestmentClick: (Investment) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        items(
            items = investments,
            key = { it.id }
        ) { investment ->
            InvestmentCard(
                modifier = Modifier.fillMaxWidth(),
                name = investment.name,
                ticker = investment.ticker,
                value = investment.value,
                principal = investment.principal,
                onViewDetailsClick = { onInvestmentClick(investment) }
            )
        }
    }
}

package org.addepar.investmenttracker.feature.details.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.addepar.investmenttracker.R
import org.addepar.investmenttracker.feature.common.models.Investment

@Composable
internal fun InvestmentDetails(
    investment: Investment,
    modifier: Modifier = Modifier,
) {
    Surface(modifier = modifier) {
        Card(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = investment.name,
                    style = MaterialTheme.typography.headlineLarge
                )

                investment.ticker?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Text(
                    text = investment.details,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.fillMaxWidth()
                )

                InvestDetailCardRow(
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.timeline),
                            contentDescription = null
                        )
                    },
                    label = stringResource(R.string.value),
                    content = investment.value
                )

                InvestDetailCardRow(
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.account),
                            contentDescription = null
                        )
                    },
                    label = stringResource(R.string.principal),
                    content = investment.value
                )
            }
        }
    }
}
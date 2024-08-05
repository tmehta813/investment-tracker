package org.addepar.investmenttracker.feature.home.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.addepar.investmenttracker.R
import org.addepar.investmenttracker.feature.common.UiState
import org.addepar.investmenttracker.feature.common.models.Investment
import org.addepar.investmenttracker.feature.home.presentation.HomePageEvent
import org.addepar.investmenttracker.feature.home.presentation.HomeViewModel
import org.addepar.investmenttracker.ui.components.ErrorMessage
import org.addepar.investmenttracker.ui.components.LoadingIndicator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenMain(
    onInvestmentClick: (Investment) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                navigationIcon = {
                    Icon(
                        painter = painterResource(R.drawable.monitoring),
                        contentDescription = stringResource(R.string.app_name),
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                },
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                    )
                }
            )
        }
    ) { innerPadding ->
        when (val state = uiState) {
            is UiState.Error ->
                ShowError(modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                    message = state.message,
                    onClick = { viewModel.handleEvent(HomePageEvent.LoadData) })

            UiState.Loading -> LoadingIndicator(
                modifier = Modifier.fillMaxSize()
            )

            is UiState.Success -> InvestmentsList(
                investments = state.data,
                onInvestmentClick = onInvestmentClick,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )
        }
    }
}

@Composable
private fun ShowError(modifier: Modifier, message: String, onClick: () -> Unit) {
    ErrorMessage(
        modifier = modifier,
        title = stringResource(R.string.error_title),
        subtitle = message,
        action = {
            Button(
                onClick = {
                    onClick.invoke()
                }
            ) {
                Text(text = stringResource(R.string.try_again))
            }
        })
}
package org.addepar.investmenttracker.feature.details.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.addepar.investmenttracker.R
import org.addepar.investmenttracker.feature.common.UiState
import org.addepar.investmenttracker.feature.details.presentation.DetailsPageEvent
import org.addepar.investmenttracker.feature.details.presentation.DetailsViewModel
import org.addepar.investmenttracker.ui.components.ErrorMessage
import org.addepar.investmenttracker.ui.components.LoadingIndicator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InvestmentDetailsMainScreen(
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.navigate_up)
                        )
                    }
                },
                title = {}
            )
        }
    ) { innerPadding ->
        when (val state = uiState) {
            is UiState.Error -> ErrorMessage(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                title = stringResource(R.string.error_title),
                subtitle = state.message,
                action = {
                    Button(
                        onClick = {
                            viewModel.handleEvent(DetailsPageEvent.LoadInvestmentDetails)
                        }
                    ) {
                        Text(text = stringResource(R.string.try_again))
                    }
                }
            )

            UiState.Loading -> LoadingIndicator(
                modifier = Modifier.fillMaxSize()
            )

            is UiState.Success -> InvestmentDetails(
                investment = state.data,
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxWidth()
            )
        }
    }
}




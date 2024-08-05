package org.addepar.investmenttracker

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.addepar.investmenttracker.feature.details.presentation.ui.InvestmentDetailsMainScreen
import org.addepar.investmenttracker.feature.home.presentation.ui.HomeScreenMain
import org.addepar.investmenttracker.ui.theme.InvestmentTrackerTheme

@Composable
fun App() {
    val controller = rememberNavController()
    InvestmentTrackerTheme {
        Navigation(
            controller = controller,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun Navigation(
    controller: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        modifier = modifier,
        navController = controller,
        startDestination = "/investments"
    ) {
        composable(
            route = "/investments"
        ) {
            HomeScreenMain(
                onInvestmentClick = { investment ->
                    controller.navigate("/investments/${investment.id}")
                }
            )
        }

        composable(
            route = "/investments/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) {
            InvestmentDetailsMainScreen(
                onNavigateUp = controller::navigateUp
            )
        }
    }
}
package ie.setu.calorietracker.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ie.setu.calorietracker.ui.screens.details.DetailsScreen
import ie.setu.calorietracker.ui.screens.calories.CaloriesScreen
import ie.setu.calorietracker.ui.screens.about.ScreenAbout
import ie.setu.calorietracker.ui.screens.report.ScreenReport

@Composable
fun NavHostProvider(
    modifier: Modifier,
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = Calories.route,
        modifier = Modifier.padding(paddingValues = paddingValues)
    ) {

        composable(route = Calories.route) {
            // Calories / Add Food screen
            CaloriesScreen(
                modifier = modifier
            )
        }

        composable(route = FoodLog.route) {
            // food log is report screen
            ScreenReport(
                modifier = modifier,
                onClickEntryDetails = { entryId ->
                    navController.navigateToEntryDetails(entryId)
                }
            )
        }

        composable(route = About.route) {
            ScreenAbout(modifier = modifier)
        }

        composable(
            route = Details.route,
            arguments = Details.arguments
        ) {
            DetailsScreen()
        }
    }
}

private fun NavHostController.navigateToEntryDetails(entryId: Int) {
    this.navigate("details/$entryId")
}


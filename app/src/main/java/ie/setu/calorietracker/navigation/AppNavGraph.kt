package ie.setu.calorietracker.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ie.setu.calorietracker.data.FoodModel
import ie.setu.calorietracker.ui.screens.calories.CaloriesScreen
import ie.setu.calorietracker.ui.screens.about.ScreenAbout
import ie.setu.calorietracker.ui.screens.report.ScreenReport

@Composable
fun NavHostProvider(
    modifier: Modifier,
    navController: NavHostController,
    paddingValues: PaddingValues,
    foods: SnapshotStateList<FoodModel>
) {
    NavHost(
        navController = navController,
        startDestination = Calories.route,
        modifier = Modifier.padding(paddingValues = paddingValues)
    ) {

        composable(route = Calories.route) {
            // Calories / Add Food screen
            CaloriesScreen(
                modifier = modifier,
                foods = foods
            )
        }

        composable(route = FoodLog.route) {
            // Food log / Report screen
            ScreenReport(
                modifier = modifier,
                foods = foods
            )
        }

        composable(route = About.route) {
            ScreenAbout(modifier = modifier)
        }
    }
}

package ie.setu.calorietracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ie.setu.calorietracker.data.FoodModel
import ie.setu.calorietracker.navigation.FoodLog
import ie.setu.calorietracker.navigation.NavHostProvider
import ie.setu.calorietracker.navigation.allDestinations
import ie.setu.calorietracker.ui.components.general.MenuItem
import ie.setu.calorietracker.ui.components.general.TopAppBarProvider
import ie.setu.calorietracker.ui.theme.CalorieTrackerTheme
import ie.setu.donationx.ui.components.general.BottomAppBarProvider

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CalorieTrackerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CalorieTrackerApp(modifier = Modifier)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalorieTrackerApp(modifier: Modifier = Modifier, navController: NavHostController = rememberNavController()) {
    val foods = remember { mutableStateListOf<FoodModel>() }
    var selectedMenuItem by remember { mutableStateOf(MenuItem.Calories) }
    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentNavBackStackEntry?.destination
    val currentBottomScreen =
        allDestinations.find { it.route == currentDestination?.route } ?: FoodLog
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBarProvider(
                currentScreen = currentBottomScreen,
                canNavigateBack = navController.previousBackStackEntry != null
            ) { navController.navigateUp() }
        },
        content = { paddingValues ->
            NavHostProvider(
                modifier = modifier,
                navController = navController,
                paddingValues = paddingValues,
                foods = foods)
        },
        bottomBar = {
            BottomAppBarProvider(navController,
                currentScreen = currentBottomScreen,)
        }
    )

}


@Preview(showBackground = true)
@Composable
fun CalorieTrackerAppPreview() {
    CalorieTrackerTheme {
        CalorieTrackerApp(modifier = Modifier)
    }
}

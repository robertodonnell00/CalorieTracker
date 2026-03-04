package ie.setu.calorietracker.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector

interface AppDestination {
    val icon: ImageVector
    val label: String
    val route: String
}

object FoodLog : AppDestination {
    override val icon = Icons.AutoMirrored.Filled.List
    override val label = "Food Log"
    override val route = "food_log"
}

object Calories : AppDestination {
    override val icon = Icons.Filled.AddCircle
    override val label = "Calories"
    override val route = "calories"
}

object About : AppDestination {
    override val icon = Icons.Filled.Info
    override val label = "About"
    override val route = "about"
}

val bottomAppBarDestinations = listOf(Calories, FoodLog, About)
val allDestinations = listOf(Calories, FoodLog, About)


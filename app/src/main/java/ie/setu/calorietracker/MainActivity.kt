package ie.setu.calorietracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ie.setu.calorietracker.data.FoodModel
import ie.setu.calorietracker.ui.screens.CaloriesScreen
import ie.setu.calorietracker.ui.theme.CalorieTrackerTheme

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

@Composable
fun CalorieTrackerApp(modifier: Modifier = Modifier) {
    val foods = remember { mutableStateListOf<FoodModel>() }

    CaloriesScreen(
        modifier = modifier,
        foods = foods
    )
}


@Preview(showBackground = true)
@Composable
fun CalorieTrackerAppPreview() {
    CalorieTrackerTheme {
        CalorieTrackerApp(modifier = Modifier)
    }
}

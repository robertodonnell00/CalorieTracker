package ie.setu.calorietracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ie.setu.calorietracker.data.FoodModel
import ie.setu.calorietracker.ui.components.general.MenuItem
import ie.setu.calorietracker.ui.screens.CaloriesScreen
import ie.setu.calorietracker.ui.screens.ScreenReport
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalorieTrackerApp(modifier: Modifier = Modifier) {
    val foods = remember { mutableStateListOf<FoodModel>() }
    var selectedMenuItem by remember { mutableStateOf(MenuItem.Calories) }
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    if (selectedMenuItem == MenuItem.Calories) {
                        IconButton(onClick = {
                            selectedMenuItem = MenuItem.Report
                        }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.List,
                                contentDescription = "View food log",
                                tint = Color.White,
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    }
                    else {
                        IconButton(onClick = {
                            selectedMenuItem = MenuItem.Calories
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = "Add food",
                                tint = Color.White,
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    }
                }
            )
        }
    ) { paddingValues ->

        when (selectedMenuItem) {
            MenuItem.Calories ->
                CaloriesScreen(
                    modifier = Modifier.padding(paddingValues),
                    foods = foods
                )

            MenuItem.Report ->
                ScreenReport(
                    modifier = Modifier.padding(paddingValues),
                    foods = foods
                )
        }
    }

}


@Preview(showBackground = true)
@Composable
fun CalorieTrackerAppPreview() {
    CalorieTrackerTheme {
        CalorieTrackerApp(modifier = Modifier)
    }
}

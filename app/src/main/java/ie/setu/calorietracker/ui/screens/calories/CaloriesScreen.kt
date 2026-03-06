package ie.setu.calorietracker.ui.screens.calories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import ie.setu.calorietracker.data.FoodModel
import ie.setu.calorietracker.ui.components.calories.CaloriePicker
import ie.setu.calorietracker.ui.components.calories.EntryButton
import ie.setu.calorietracker.ui.components.calories.EntryTypeRadioGroup
import ie.setu.calorietracker.ui.components.calories.FoodTextInput
import ie.setu.calorietracker.ui.components.calories.ProgressBar
import ie.setu.calorietracker.ui.components.calories.WelcomeHeader
import ie.setu.calorietracker.ui.screens.report.ReportViewModel
import ie.setu.calorietracker.ui.components.calories.FoodNumberInput

@Composable
fun CaloriesScreen(
    modifier: Modifier = Modifier,
    reportViewModel: ReportViewModel = hiltViewModel(),
    caloriesViewModel: CaloriesViewModel = hiltViewModel()
) {
    val foods = reportViewModel.uiEntries.collectAsState().value

    var foodType by remember { mutableStateOf("Meal") }
    var calories by remember { mutableIntStateOf(100) }
    var name by remember { mutableStateOf("") }
    var protein by remember { mutableStateOf("") }
    var carbs by remember { mutableStateOf("") }
    var sugar by remember { mutableStateOf("") }
    var salt by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }

    val totalCalories = foods.sumOf { it.calories }

    Column(
        modifier = modifier
            .padding(start = 24.dp, end = 24.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ){
        WelcomeHeader()

        Row(verticalAlignment = Alignment.CenterVertically) {
            EntryTypeRadioGroup(onEntryTypeChange = { foodType = it })
            Spacer(Modifier.weight(1f))
            CaloriePicker(onCaloriesChange = { calories = it })
        }

        ProgressBar(
            totalCalories = totalCalories,
            dailyCalorieLimit = 5000
        )

        FoodTextInput(
            label = "Food Name",
            value = name,
            onValueChange = { name = it }
        )

        FoodNumberInput(
            label = "Protein (g)",
            value = protein,
            onValueChange = { protein = it }
        )

        FoodNumberInput(
            label = "Carbs (g)",
            value = carbs,
            onValueChange = { carbs = it }
        )

        FoodNumberInput(
            label = "Sugar (g)",
            value = sugar,
            onValueChange = { sugar = it }
        )

        FoodNumberInput(
            label = "Salt (g)",
            value = salt,
            onValueChange = { salt = it }
        )

        FoodTextInput(
            label = "Note",
            value = note,
            maxLines = 2,
            onValueChange = { note = it }
        )

        EntryButton(
            entry = FoodModel(
                name = name,
                type = foodType,
                calories = calories,
                protein = protein.toIntOrNull() ?: 0,
                carbs = carbs.toIntOrNull() ?: 0,
                sugar = sugar.toIntOrNull() ?: 0,
                salt = salt.toIntOrNull() ?: 0,
                note = note
            ),
            totalCalories = totalCalories,
            onAddEntry = { caloriesViewModel.insert(it) }
        )
    }
}

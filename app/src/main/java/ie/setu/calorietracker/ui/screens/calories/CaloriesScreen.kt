package ie.setu.calorietracker.ui.screens.calories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import ie.setu.calorietracker.data.FoodModel
import ie.setu.calorietracker.data.fakeFoods
import ie.setu.calorietracker.ui.components.calories.CaloriePicker
import ie.setu.calorietracker.ui.components.calories.EntryButton
import ie.setu.calorietracker.ui.components.calories.EntryTypeRadioGroup
import ie.setu.calorietracker.ui.components.calories.MessageInput
import ie.setu.calorietracker.ui.components.calories.ProgressBar
import ie.setu.calorietracker.ui.components.calories.WelcomeHeader
import ie.setu.calorietracker.ui.screens.report.ReportViewModel
import ie.setu.calorietracker.ui.theme.CalorieTrackerTheme

@Composable
fun CaloriesScreen(
    modifier: Modifier = Modifier,
    reportViewModel: ReportViewModel = hiltViewModel(),
    caloriesViewModel: CaloriesViewModel = hiltViewModel()
) {
    val foods = reportViewModel.uiEntries.collectAsState().value
    var foodType by remember { mutableStateOf("Meal") }
    var calories by remember { mutableIntStateOf(100) }
    var note by remember { mutableStateOf("") }
    val totalCalories = foods.sumOf { it.calories }

    Column(modifier = modifier.padding(start = 24.dp, end = 24.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
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

        MessageInput(onNoteChange = { note = it })

        EntryButton(
            entry = FoodModel(type = foodType, calories = calories, note = note),
            totalCalories = totalCalories,
            onAddEntry = { caloriesViewModel.insert(it) }
        )
    }
}

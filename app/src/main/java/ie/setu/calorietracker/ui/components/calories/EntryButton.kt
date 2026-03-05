package ie.setu.calorietracker.ui.components.calories

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import ie.setu.calorietracker.R
import ie.setu.calorietracker.data.FoodModel
import ie.setu.calorietracker.data.fakeFoods
import ie.setu.calorietracker.ui.screens.report.ReportViewModel
import ie.setu.calorietracker.ui.theme.CalorieTrackerTheme
import timber.log.Timber

@Composable
fun EntryButton(
    modifier: Modifier = Modifier,
    entry: FoodModel,
    totalCalories: Int,
    onAddEntry: (FoodModel) -> Unit
) {
    Row(modifier = modifier) {
        Button(onClick = { onAddEntry(entry) },
            elevation = ButtonDefaults.buttonElevation(20.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add Entry")
            Spacer(Modifier.width(4.dp))
            Text(
                text = stringResource(R.string.addEntryButton),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White
            )
        }

        Spacer(Modifier.weight(1f))

        Text(
            buildAnnotatedString {
                withStyle(SpanStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)) {
                    append(stringResource(R.string.totalCaloriesLabel) + " ")
                }
                withStyle(SpanStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.secondary)) {
                    append("$totalCalories kcal")
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EntryButtonPreview() {
    CalorieTrackerTheme {
        EntryButton(
            modifier = Modifier,
            entry = FoodModel(type = "Meal", calories = 250, note = ""),
            totalCalories = fakeFoods.sumOf { it.calories },
            onAddEntry = {  }
        )
    }
}

package ie.setu.calorietracker.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ie.setu.calorietracker.data.FoodModel
import ie.setu.calorietracker.data.fakeFoods
import ie.setu.calorietracker.ui.components.report.FoodCardList
import ie.setu.calorietracker.ui.components.report.ReportText
import ie.setu.calorietracker.ui.theme.CalorieTrackerTheme

@Composable
fun ScreenReport(
    modifier: Modifier = Modifier,
    foods: SnapshotStateList<FoodModel>
) {
    Column {
        Column(
            modifier = modifier.padding(
                top = 48.dp,
                start = 24.dp,
                end = 24.dp
            ),
        ) {
            ReportText()
            FoodCardList(
                foods = foods
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReportScreenPreview() {
    CalorieTrackerTheme {
        ScreenReport(
            modifier = Modifier,
            foods = fakeFoods.toMutableStateList()
        )
    }
}

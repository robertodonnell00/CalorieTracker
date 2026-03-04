package ie.setu.calorietracker.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ie.setu.calorietracker.R
import ie.setu.calorietracker.data.FoodModel
import ie.setu.calorietracker.data.fakeFoods
import ie.setu.calorietracker.ui.components.general.Centre
import ie.setu.calorietracker.ui.components.report.FoodCardList
import ie.setu.calorietracker.ui.components.report.ReportText
import ie.setu.calorietracker.ui.theme.CalorieTrackerTheme

@Composable
fun ScreenReport(
    modifier: Modifier = Modifier,
    foods: SnapshotStateList<FoodModel>
) {
    if (foods.isEmpty()) {

        Centre(Modifier.fillMaxSize()) {
            Text(
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                lineHeight = 34.sp,
                textAlign = TextAlign.Center,
                text = stringResource(R.string.empty_list)
            )
        }

    } else {
        Column {
            Column(
                modifier = modifier.padding(
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

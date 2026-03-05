package ie.setu.calorietracker.ui.components.report

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import ie.setu.calorietracker.data.FoodModel
import ie.setu.calorietracker.data.fakeFoods
import ie.setu.calorietracker.ui.theme.CalorieTrackerTheme
import java.text.DateFormat

@Composable
internal fun FoodCardList(
    foods: List<FoodModel>,
    modifier: Modifier = Modifier
) {
    LazyColumn {
        items(
            items = foods,
            key = { food -> food.id }
        ) { food ->
            FoodCard(
                foodType = food.type,
                calories = food.calories,
                note = food.note,
                dateAdded = DateFormat.getDateTimeInstance().format(food.dateAdded)
            )
        }
    }
}

@Preview(
    showBackground = true,
    wallpaper = Wallpapers.BLUE_DOMINATED_EXAMPLE
)
@Composable
fun FoodCardListPreview() {
    CalorieTrackerTheme {
        FoodCardList(
            foods = fakeFoods.toMutableStateList()
        )
    }
}

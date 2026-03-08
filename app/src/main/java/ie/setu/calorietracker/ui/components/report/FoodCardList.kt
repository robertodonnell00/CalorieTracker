package ie.setu.calorietracker.ui.components.report

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
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
    modifier: Modifier = Modifier,
    onDeleteEntry: (FoodModel) -> Unit,
    onClickEntryDetails: (Int) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(
            items = foods,
            key = { food -> food.id }
        ) { food ->
            FoodCard(
                foodType = food.type,
                foodName = food.name,
                calories = food.calories,
                note = food.note,
                protein = food.protein,
                carbs = food.carbs,
                salt = food.salt,
                sugar = food.sugar,
                dateAdded = DateFormat.getDateTimeInstance().format(food.dateAdded),
                onClickDelete = { onDeleteEntry(food) },
                onClickEntryDetails = { onClickEntryDetails(food.id) }
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
            foods = fakeFoods,
            onDeleteEntry = {},
            onClickEntryDetails = {}
        )
    }
}
package ie.setu.calorietracker.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
import kotlin.random.Random

@Entity
data class FoodModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = Random.nextInt(1, 100000),
    val name: String = "",
    val type: String = "Meal",      // Meal or Snack
    val calories: Int = 0,
    val protein: Int = 0,           // grams
    val carbs: Int = 0,             // grams
    val sugar: Int = 0,             // grams
    val salt: Int = 0,              // grams
    val note: String = "",
    val dateAdded: Date = Date()
)

val fakeFoods = List(30) { i ->
    FoodModel(
        id = 12345 + i,
        name = "Food Item $i",
        type = if (i % 2 == 0) "Meal" else "Snack",
        calories = 100 + (i * 20),
        protein = 5 + i,
        carbs = 15 + (i * 2),
        sugar = 3 + (i % 5),
        salt = 1 + (i % 3),
        note = "Sample note $i",
        dateAdded = Date()
    )
}
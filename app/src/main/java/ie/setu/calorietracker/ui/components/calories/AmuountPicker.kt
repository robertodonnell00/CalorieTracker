package ie.setu.calorietracker.ui.components.calories

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.chargemap.compose.numberpicker.ListItemPicker
import ie.setu.calorietracker.ui.theme.CalorieTrackerTheme

@Composable
fun CaloriePicker(
    onCaloriesChange: (Int) -> Unit
) {
    val calorieOptions = listOf("100", "200", "300", "500", "750", "1000")
    var selectedCalories by remember { mutableStateOf(calorieOptions[0]) }

    ListItemPicker(
        label = { "$it kcal" },
        dividersColor = MaterialTheme.colorScheme.primary,
        textStyle = TextStyle(Color.Black, 20.sp),
        value = selectedCalories,
        onValueChange = {
            selectedCalories = it
            onCaloriesChange(selectedCalories.toInt())
        },
        list = calorieOptions
    )
}

@Preview(showBackground = true)
@Composable
fun CaloriePickerPreview() {
    CalorieTrackerTheme {
        CaloriePicker(onCaloriesChange = {})
    }
}


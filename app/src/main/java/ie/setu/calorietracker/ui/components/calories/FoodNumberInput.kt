package ie.setu.calorietracker.ui.components.calories

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import ie.setu.calorietracker.ui.theme.CalorieTrackerTheme

@Composable
fun FoodNumberInput(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
        ),
        value = value,
        onValueChange = { input ->
            if (input.all { it.isDigit() } || input.isEmpty()) {
                onValueChange(input)
            }
        },
        modifier = modifier.fillMaxWidth(),
        label = { Text(label) },
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@Preview(showBackground = true)
@Composable
fun FoodNumberInputPreview() {
    CalorieTrackerTheme {
        FoodNumberInput(
            modifier = Modifier,
            label = "Protein (g)",
            value = "",
            onValueChange = {}
        )
    }
}
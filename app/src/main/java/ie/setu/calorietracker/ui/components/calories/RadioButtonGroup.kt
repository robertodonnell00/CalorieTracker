package ie.setu.calorietracker.ui.components.calories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ie.setu.calorietracker.R
import ie.setu.calorietracker.ui.theme.CalorieTrackerTheme

@Composable
fun EntryTypeRadioGroup(
    modifier: Modifier = Modifier,
    onEntryTypeChange: (String) -> Unit
) {

    val entryOptions = listOf(
        stringResource(R.string.entryTypeMeal),
        stringResource(R.string.entryTypeSnack)
    )

    var selectedEntryType by remember { mutableStateOf(entryOptions[0]) }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        entryOptions.forEach { entryText ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = (entryText == selectedEntryType),
                    onClick = {
                        selectedEntryType = entryText
                        onEntryTypeChange(selectedEntryType)
                    }
                )
                Text(
                    text = entryText,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EntryTypeRadioGroupPreview() {
    CalorieTrackerTheme {
        EntryTypeRadioGroup(
            modifier = Modifier,
            onEntryTypeChange = {}
        )
    }
}
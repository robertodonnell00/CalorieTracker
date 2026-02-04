package ie.setu.calorietracker.ui.components.calories

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ie.setu.calorietracker.R
import androidx.compose.ui.tooling.preview.Preview
import ie.setu.calorietracker.ui.theme.CalorieTrackerTheme

@Composable
fun MessageInput(
    modifier: Modifier = Modifier,
    onNoteChange: (String) -> Unit
) {
    var note by remember { mutableStateOf("") }

    OutlinedTextField(
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
        ),
        maxLines = 2,
        value = note,
        onValueChange = {
            note = it
            onNoteChange(note)
        },
        modifier = modifier.fillMaxWidth(),
        label = { Text(stringResource(R.string.enterNote)) }
    )
}

@Preview(showBackground = true)
@Composable
fun MessageInputPreview() {
    CalorieTrackerTheme {
        MessageInput(
            modifier = Modifier,
            onNoteChange = {}
        )
    }
}


package ie.setu.calorietracker.ui.screens.details

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import ie.setu.calorietracker.ui.components.details.DetailsScreenText
import ie.setu.calorietracker.ui.components.details.ReadOnlyTextField
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.MaterialTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    detailsViewModel: DetailsViewModel = hiltViewModel()
) {
    val entry = detailsViewModel.entry.value
    var editableNote by remember(entry.id) { mutableStateOf(entry.note) }

    val errorEmptyNote = "Note cannot be empty..."
    val errorShortNote = "Note must be at least 2 characters"

    var text by rememberSaveable(entry.id) { mutableStateOf(entry.note) }
    var onNoteChanged by rememberSaveable { mutableStateOf(false) }
    var isEmptyError by rememberSaveable { mutableStateOf(false) }
    var isShortError by rememberSaveable { mutableStateOf(false) }

    fun validate(input: String) {
        isEmptyError = input.isEmpty()
        isShortError = input.length < 2
        onNoteChanged = !(isEmptyError || isShortError) && input != entry.note
    }

    Column(
        modifier = modifier.padding(start = 24.dp, end = 24.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        DetailsScreenText()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp, end = 10.dp)
        ) {
            ReadOnlyTextField(value = entry.type, label = "Type")
            ReadOnlyTextField(value = entry.calories.toString(), label = "Calories")
            ReadOnlyTextField(value = entry.dateAdded.toString(), label = "Date Added")

            // editable note
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = text,
                onValueChange = {
                    text = it
                    validate(text)
                },
                maxLines = 2,
                label = { Text(text = "Note") },
                isError = isEmptyError || isShortError,
                supportingText = {
                    when {
                        isEmptyError -> Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = errorEmptyNote,
                            color = MaterialTheme.colorScheme.error
                        )
                        isShortError -> Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = errorShortNote,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                trailingIcon = {
                    if (isEmptyError || isShortError) {
                        Icon(Icons.Filled.Warning, contentDescription = "error", tint = MaterialTheme.colorScheme.error)
                    } else {
                        Icon(Icons.Filled.Edit, contentDescription = "edit", tint = Color.Black)
                    }
                },
                keyboardActions = KeyboardActions { validate(text) },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = MaterialTheme.colorScheme.secondary
                )
            )

            Spacer(modifier = Modifier.height(48.dp))

            Button(
                onClick = {
                    detailsViewModel.updateEntry(entry.copy(note = text))
                    onNoteChanged = false
                },
                elevation = ButtonDefaults.buttonElevation(20.dp),
                enabled = onNoteChanged
            ) {
                Icon(Icons.Default.Save, contentDescription = "Save")
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Save",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White
                )
            }
        }
    }
}
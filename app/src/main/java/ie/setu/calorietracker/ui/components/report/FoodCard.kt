package ie.setu.calorietracker.ui.components.report

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ie.setu.calorietracker.R
import ie.setu.calorietracker.ui.theme.CalorieTrackerTheme
import java.text.DateFormat
import java.util.Date

@Composable
fun FoodCard(
    foodName: String,
    foodType: String,
    calories: Int,
    protein: Int,
    carbs: Int,
    sugar: Int,
    salt: Int,
    note: String,
    dateAdded: String,
    onClickDelete: () -> Unit,
    onClickEntryDetails: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 2.dp)
    ) {
        FoodCardContent(
            foodName = foodName,
            foodType = foodType,
            calories = calories,
            protein = protein,
            carbs = carbs,
            sugar = sugar,
            salt = salt,
            note = note,
            dateAdded = dateAdded,
            onClickDelete = onClickDelete,
            onClickEntryDetails = onClickEntryDetails
        )
    }
}

@Composable
private fun FoodCardContent(
    foodName: String,
    foodType: String,
    calories: Int,
    protein: Int,
    carbs: Int,
    sugar: Int,
    salt: Int,
    note: String,
    dateAdded: String,
    onClickDelete: () -> Unit,
    onClickEntryDetails: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(4.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.Restaurant,
                    contentDescription = "Food Entry",
                    modifier = Modifier.padding(end = 8.dp)
                )

                Column {
                    Text(
                        text = if (foodName.isNotBlank()) foodName else foodType,
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.ExtraBold
                        )
                    )
                    Text(
                        text = foodType,
                        style = MaterialTheme.typography.labelMedium
                    )
                }

                Spacer(Modifier.weight(1f))

                Text(
                    text = "$calories kcal",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
            }

            Text(
                text = "Added $dateAdded",
                style = MaterialTheme.typography.labelSmall
            )

            if (expanded) {
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = "Protein: ${protein}g"
                )
                Text(text = "Carbs: ${carbs}g")
                Text(text = "Sugar: ${sugar}g")
                Text(text = "Salt: ${salt}g")

                if (note.isNotBlank()) {
                    Text(
                        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
                        text = note
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    FilledTonalButton(onClick = onClickEntryDetails) {
                        Text(text = "Show More")
                    }

                    FilledTonalIconButton(onClick = onClickDelete) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "Delete Entry"
                        )
                    }
                }
            }
        }

        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = if (expanded) {
                    Icons.Filled.ExpandLess
                } else {
                    Icons.Filled.ExpandMore
                },
                contentDescription = if (expanded) {
                    stringResource(R.string.show_less)
                } else {
                    stringResource(R.string.show_more)
                }
            )
        }
    }
}

@Preview
@Composable
fun FoodCardPreview() {
    CalorieTrackerTheme {
        FoodCard(
            foodName = "Chicken Rice Bowl",
            foodType = "Meal",
            calories = 500,
            protein = 42,
            carbs = 58,
            sugar = 6,
            salt = 2,
            note = "Post-workout meal with extra chicken.",
            dateAdded = DateFormat.getDateTimeInstance().format(Date()),
            onClickDelete = {},
            onClickEntryDetails = {}
        )
    }
}
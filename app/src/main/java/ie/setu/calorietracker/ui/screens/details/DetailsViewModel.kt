package ie.setu.calorietracker.ui.screens.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ie.setu.calorietracker.data.FoodModel
import ie.setu.calorietracker.data.repository.RoomRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: RoomRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var entry = mutableStateOf(FoodModel())
    val id: Int = checkNotNull(savedStateHandle["id"])

    init {
        viewModelScope.launch {
            repository.get(id).collect { food ->
                entry.value = food
            }
        }
    }

    fun updateEntry(updated: FoodModel) {
        viewModelScope.launch { repository.update(updated) }
    }
}
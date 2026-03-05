package ie.setu.calorietracker.ui.screens.calories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ie.setu.calorietracker.data.FoodModel
import ie.setu.calorietracker.data.repository.RoomRepository
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class CaloriesViewModel @Inject constructor(
    private val repository: RoomRepository
) : ViewModel() {
    fun insert(entry: FoodModel) = viewModelScope.launch {
        repository.insert(entry)
    }
}
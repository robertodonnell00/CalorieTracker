package ie.setu.calorietracker.ui.screens.report

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ie.setu.calorietracker.data.FoodModel
import ie.setu.calorietracker.data.repository.RoomRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ReportViewModel @Inject constructor(
    private val repository: RoomRepository
) : ViewModel() {

    private val _entries = MutableStateFlow<List<FoodModel>>(emptyList())
    val uiEntries: StateFlow<List<FoodModel>> = _entries.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAll().collect { list ->
                _entries.value = list
            }
        }
    }

    fun deleteEntry(entry: FoodModel) {
        viewModelScope.launch {
            repository.delete(entry)
        }
    }
}

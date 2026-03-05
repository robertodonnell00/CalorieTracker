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
class ReportViewModel @Inject
constructor(private val repository: RoomRepository) : ViewModel() {
    private val _donations
            = MutableStateFlow<List<FoodModel>>(emptyList())
    val uiDonations: StateFlow<List<FoodModel>>
            = _donations.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAll().collect { listOfDonations ->
                _donations.value = listOfDonations
            }
        }
    }
}

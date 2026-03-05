package ie.setu.calorietracker.data.repository

import ie.setu.calorietracker.data.FoodModel
import ie.setu.calorietracker.data.room.CalorieDAO
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class RoomRepository @Inject
constructor(private val calorieDAO: CalorieDAO) {
    fun getAll(): Flow<List<FoodModel>>
            = calorieDAO.getAll()

    suspend fun insert(donation: FoodModel)
    { calorieDAO.insert(donation) }

    suspend fun update(donation: FoodModel)
    { calorieDAO.update(donation) }

    suspend fun delete(donation: FoodModel)
    { calorieDAO.delete(donation) }
}
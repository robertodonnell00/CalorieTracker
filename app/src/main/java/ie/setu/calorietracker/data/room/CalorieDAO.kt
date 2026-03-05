package ie.setu.calorietracker.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ie.setu.calorietracker.data.FoodModel
import kotlinx.coroutines.flow.Flow

@Dao
interface CalorieDAO {
    @Query("SELECT * FROM foodmodel")
    fun getAll(): Flow<List<FoodModel>>

    @Insert
    suspend fun insert(calorieEntry: FoodModel)

    @Update
    suspend fun update(calorieEntry: FoodModel)

    @Delete
    suspend fun delete(calorieEntry: FoodModel)
}
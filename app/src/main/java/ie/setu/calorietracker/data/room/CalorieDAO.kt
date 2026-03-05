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

    @Query("UPDATE foodmodel SET note = :note WHERE id = :id")
    suspend fun updateNote(id: Int, note: String)

    @Delete
    suspend fun delete(calorieEntry: FoodModel)

    @Query("SELECT * FROM foodmodel WHERE id = :id")
    fun get(id: Int): Flow<FoodModel>
}
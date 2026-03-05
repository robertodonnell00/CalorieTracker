package ie.setu.calorietracker.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ie.setu.calorietracker.data.FoodModel

@Database(entities = [FoodModel::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getCalorieDAO(): CalorieDAO
}
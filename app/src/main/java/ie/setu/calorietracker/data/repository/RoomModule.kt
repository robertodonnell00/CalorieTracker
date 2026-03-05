package ie.setu.calorietracker.data.repository

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ie.setu.calorietracker.data.room.AppDatabase
import ie.setu.calorietracker.data.room.CalorieDAO
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "calories_database")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideCalorieDAO(appDatabase: AppDatabase): CalorieDAO =
        appDatabase.getCalorieDAO()

    @Provides
    @Singleton
    fun provideRoomRepository(calorieDAO: CalorieDAO): RoomRepository =
        RoomRepository(calorieDAO)
}
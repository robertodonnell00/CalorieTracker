package ie.setu.calorietracker.main

import android.app.Application
import timber.log.Timber

class CalorieTrackerMainApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Timber.i("Starting Calorie Tracker")
    }
}
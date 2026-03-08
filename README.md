# CalorieTracker

CalorieTracker is an Android application built with **Kotlin** and **Jetpack Compose** that allows users to log and monitor their daily food intake. The app enables users to add food entries, track calorie intake, and view previously logged meals in a structured report.

## Features

- Add food entries with:
  - Food name
  - Meal type (Meal or Snack)
  - Calories
  - Protein, carbs, sugar, and salt
  - Optional notes
- View a list of all logged food entries
- Delete entries from the food log
- Expand entries to view nutritional details
- Daily calorie progress tracking
- Navigation between screens using a **bottom navigation bar**
- About screen with app information

## Architecture

The application follows modern Android development practices:

- **Jetpack Compose** for UI
- **MVVM architecture**
- **Hilt** for dependency injection
- **Room database** for persistent storage
- **Navigation Compose** for screen navigation

## Project Structure
data/
├─ FoodModel
├─ DAO
├─ Room Database
└─ Repository

ui/
├─ components/
├─ screens/
│ ├─ calories
│ ├─ report
│ └─ about
└─ navigation/


## Screens

- **Calories Screen** – Add new food entries and view calorie progress
- **Food Log** – View and manage all logged entries
- **About** – Basic information about the application

## Technologies Used

- Kotlin
- Jetpack Compose
- Room Database
- Hilt Dependency Injection
- Android Navigation Compose
- Material 3 UI

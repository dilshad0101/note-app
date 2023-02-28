package com.app.note.navigation

sealed class Screen(var route: String) {
    object HomeScreen: Screen("HomeScreen")
    object SearchScreen: Screen("SearchScreen")
    object CreateScreen: Screen("CreateScreen")
    object NoteViewScreen: Screen("NoteViewScreen")
}
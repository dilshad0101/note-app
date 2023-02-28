package com.app.note.navigation


import androidx.compose.runtime.livedata.observeAsState
import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavType
import com.google.accompanist.navigation.animation.navigation
import androidx.navigation.compose.NavHost
import com.google.accompanist.navigation.animation.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.app.note.data.Note
import com.app.note.data.UserViewModel
import com.app.note.presentation.editor.CreateNoteScreen
import com.app.note.presentation.editor.NoteViewScreen
import com.app.note.presentation.home.HomeScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import org.json.JSONArray
import java.io.File

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation(owner: ViewModelStoreOwner){
     val navController = rememberAnimatedNavController()

     var userViewModel = ViewModelProvider(owner)[UserViewModel::class.java]
     val _noteList = userViewModel.readAllData

     val noteList by _noteList.observeAsState(initial = emptyList())

     AnimatedNavHost(navController = navController,
          startDestination = Screen.HomeScreen.route){

          composable(route = Screen.HomeScreen.route,
               enterTransition = {
                    slideInHorizontally(initialOffsetX = {
                              fullWidth ->
                         fullWidth/-2
                    })
               }
               ){
               HomeScreen(navController,
                    _noteList,
                    deleteNote = {
                         userViewModel.deleteNote(it)
                    }
                         )
          }
          composable(route = Screen.CreateScreen.route,
          enterTransition = { scaleIn(snap()) }){
               CreateNoteScreen(navController,
               onSave = {title: String, content: String ->
                    userViewModel.addNote(Note(0,title,content))
                    Log.d("New Note Added",title)
               })
          }
          composable(
               route = Screen.NoteViewScreen.route+"/{id}",
               arguments = listOf(navArgument("id")
               {type= NavType.StringType})
          ){
                    backStackEntry ->
               val id = backStackEntry.arguments?.getString("id")?.toInt()
               val note = noteList.find { note -> note.id == id }
               if (note != null){
                    NoteViewScreen(title = note.title,
                         content = note.content,
                         navController = navController)
               }

          }
     }

}
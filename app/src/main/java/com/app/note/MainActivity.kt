package com.app.note

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.note.navigation.Navigation
import com.app.note.ui.theme.NoteAppTheme
import com.app.note.data.Note
import com.app.note.data.UserViewModel
import com.app.note.presentation.home.colorList
import kotlin.math.absoluteValue

class MainActivity : ComponentActivity(){
   // lateinit var userViewModel : UserViewModel
    //clone while managing second action

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        colorList = listOf(
            Color(0xFFF06292),
            Color(0xFFE17EF1),
            Color(0xFF5895C7),
            Color(0xFF997CCA),
            Color(0xFF66BB6A),
            Color(0xFF26A69A),
            Color(0xFFFFAB91),
            Color(0xFF7986CB),
            Color(0xFFBD8AFC),
            Color(0xFFF08765),
            Color(0xFFD892AD),
        ).shuffled()

        setContent {
            NoteAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.primary
                ) {
                    Navigation(this)
                  }

            }
        }
    }
}


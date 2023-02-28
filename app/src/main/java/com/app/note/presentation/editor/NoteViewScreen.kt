package com.app.note.presentation.editor

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.app.note.R
import com.app.note.navigation.Screen
import com.app.note.ui.ClassicButton
import com.app.note.ui.theme.Nunito


@Composable
fun NoteViewScreen(title: String,
                   content: String,
                   navController: NavController
                   ){
    GenericShape{_,_,-> }

    BackHandler(true) {
        navController.navigate(Screen.HomeScreen.route)
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.primary)
        .padding(vertical = 30.dp, horizontal = 28.dp)
    ) {
        ClassicButton(
            onClick = { navController.navigate(Screen.HomeScreen.route) },
            iconID = R.drawable.round_keyboard_arrow_left_24
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(30.dp))

        Text(text = title,
            style = MaterialTheme.typography.caption,
            softWrap = true,
            color = MaterialTheme.colors.onPrimary
            )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(27.dp))

        SelectionContainer() {
            Text(text = content,
                style = TextStyle(
                    fontFamily = Nunito,
                    fontWeight = FontWeight.W300,
                    fontSize = 21.sp,
                    letterSpacing = 0.5.sp
                ),
                softWrap = true,
                color = MaterialTheme.colors.onPrimary
            )
        }

    }

}
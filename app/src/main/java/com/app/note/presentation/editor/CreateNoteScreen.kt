package com.app.note.presentation.editor

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.note.R
import com.app.note.navigation.Screen
import com.app.note.ui.ClassicButton

@Composable
fun CreateNoteScreen(navController: NavController,
                     onSave:(title: String,content:String) -> Unit
){

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column(modifier =Modifier.padding(15.dp)
        .background(MaterialTheme.colors.primary)) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier= Modifier
                .fillMaxWidth()
                .padding(vertical = 40.dp)
        ){
            ClassicButton(onClick = { navController.navigate(Screen.HomeScreen.route) },
                iconID = R.drawable.round_keyboard_arrow_left_24)
            ClassicButton(
                onClick = {
                          if (title.isNotBlank()){ onSave.invoke(title,description)}
                    navController.navigate(Screen.HomeScreen.route)
            },
                iconID = R.drawable.outline_save_24)
        }
        Column(modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.Start
        ) {

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().height(90.dp),
                textStyle = MaterialTheme.typography.caption,
                value = title,
                onValueChange = {title = it},
                placeholder = {
                    Text(
                    text = "Title",
                    color = Color(0xFF575757),
                    style = MaterialTheme.typography.caption
                )},
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = MaterialTheme.colors.onPrimary,
                    backgroundColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    cursorColor = MaterialTheme.colors.onPrimary
                ),
                maxLines = 3

            )
            OutlinedTextField(
                modifier = Modifier.fillMaxSize(),
                value = description,
                maxLines = 4,
                textStyle = MaterialTheme.typography.subtitle1,
                onValueChange = {description = it},
                placeholder = {
                    Text(text = "Type something...",
                        color = Color(0xFF575757),
                        style = MaterialTheme.typography.subtitle1)
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = MaterialTheme.colors.onPrimary,
                    backgroundColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    cursorColor = MaterialTheme.colors.onPrimary
                )
                )

        }
    }


}
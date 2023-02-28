package com.app.note.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.note.ui.theme.Nunito


@Preview
@Composable
fun AboutDialogue(){
    val string = "Introducing Note App, created with a keen focus on aesthetics, boasts a clean and minimalist design. This interface is intended to facilitate the note-taking process, enabling the user to stay organized and productive with ease. Thank you for selecting this application; it is our utmost hope that it satisfies your note-taking requirements."

    Surface(modifier = Modifier.padding(horizontal = 12.dp)
        .shadow(8.dp,RoundedCornerShape(7),true,Color.White),
        shape = RoundedCornerShape(7),
        color = Color(0xFF222222)
        ) {
        Column(modifier = Modifier.padding(17.dp)) {
            Text(text = string,
                softWrap = true,
                style = TextStyle(
                    color = MaterialTheme.colors.onPrimary,
                    fontSize = 15.sp,
                    fontFamily = Nunito,
                    letterSpacing = 0.1.sp,
                    fontWeight = FontWeight.Light
                )
                )
            Text(text = "-Dilshad",
                softWrap = true,
                style = TextStyle(
                    color = MaterialTheme.colors.onPrimary,
                    fontSize = 14.sp,
                    fontFamily = Nunito,
                    letterSpacing = 0.1.sp,
                    fontWeight = FontWeight.Light),
                modifier = Modifier.fillMaxWidth().padding(top= 7.dp).wrapContentWidth(Alignment.End)
            )
        }

    }
}
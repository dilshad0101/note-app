package com.app.note.presentation.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun FABHome(onClick: () -> Unit){
    FloatingActionButton(
        onClick = onClick ,
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.padding(bottom = 20.dp, end = 10.dp).size(60.dp)
    ) {
        Icon(
            Icons.Outlined.Add,
            contentDescription = "create new note",
            tint = Color.White,
            modifier = Modifier.size(40.dp)
        )
    }

}
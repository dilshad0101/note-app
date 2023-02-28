package com.app.note.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.app.note.R
import org.w3c.dom.Text

@Composable
fun ClassicButton(onClick: () -> Unit, iconID: Int){
    Button(
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary),
        onClick = onClick,
        shape = RoundedCornerShape(20),
        modifier = Modifier.size(40.dp),
        contentPadding = PaddingValues(0.dp)
    ) {
        Icon(painter = painterResource(iconID),
            contentDescription = null,
            tint = MaterialTheme.colors.onPrimary,
            modifier = Modifier.size(22.dp)
        )

    }
}


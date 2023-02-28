package com.app.note.presentation.home

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.modifier.ModifierLocalConsumer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.note.data.Note
import kotlin.math.min


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NoteCard(
    modifier: Modifier = Modifier,
    color: Color,
    title: String,
    deleteNote: () -> Unit,
    toNavigate: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    var isDeleteMode by remember {
        mutableStateOf(false)
    }
    Card(
        backgroundColor = color,
        elevation = 0.dp,
        modifier = Modifier
            .heightIn(min = 100.dp)
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        isDeleteMode = true
                    },
                    onTap = { toNavigate.invoke() }
                )
            },
        shape = RoundedCornerShape(10)
    ) {
        AnimatedContent(targetState = isDeleteMode,
            transitionSpec = {
                (expandHorizontally() with shrinkHorizontally())
                    .using(
                        SizeTransform(clip = false)
                    )
            }
        ) {

            if (!it) {
                Column(
                    modifier = Modifier
                        .padding(
                            vertical = 20.dp,
                            horizontal = 34.dp
                        ),
                    verticalArrangement = Arrangement.SpaceAround
                ) {

                    Text(
                        text = title,
                        color = Color.Black,
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.Start,
                        softWrap = true,
                        modifier = modifier.padding(end = 20.dp)
                    )
                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Red)
                        .clickable {
                            deleteNote.invoke();
                            isDeleteMode = false
                        },
                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        null,
                        tint = MaterialTheme.colors.onPrimary,
                        modifier = Modifier
                            .size(40.dp)
                            .align(Alignment.Center)
                    )
                    IconButton(
                        onClick = { isDeleteMode = false },
                        interactionSource = interactionSource,
                        modifier = Modifier.align(Alignment.TopEnd)
                    ) {
                        Icon(Icons.Default.Close, null)
                    }


                }

            }
        }


    }
}

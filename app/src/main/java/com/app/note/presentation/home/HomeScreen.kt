package com.app.note.presentation.home

import android.app.Activity
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.app.note.R
import com.app.note.data.Note
import com.app.note.navigation.Screen
import com.app.note.ui.ClassicButton
import com.app.note.ui.theme.Nunito
import kotlinx.coroutines.launch
import java.util.*


@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController,
               noteListLivedata: LiveData<List<Note>>,
               deleteNote: (Note) -> Unit
               ){
    val activity = (LocalContext.current as? Activity)
    BackHandler {
        activity?.finish()
    }

    val noteList by noteListLivedata.observeAsState(initial = emptyList())
    var isSearchBarVisible by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf(TextFieldValue("")) }
    val listState = rememberLazyListState()
    val interactionSource = remember{ MutableInteractionSource() }
    val scope = rememberCoroutineScope()
    var openDialogue by remember {
        mutableStateOf(false)
    }

    Scaffold(
        floatingActionButtonPosition = FabPosition.End,
        backgroundColor = MaterialTheme.colors.primary,
        floatingActionButton = { FABHome(
            onClick = { navController.navigate(Screen.CreateScreen.route)
            },
        ) }

    ) {
            scaffoldPadding ->
        if (openDialogue) {
            Dialog(onDismissRequest = { openDialogue = false }) {
                AboutDialogue()
            }
        }
        if(noteList.isEmpty()){
            Box(
                Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)) {
                Text(text = "Create Your First Note!",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = Nunito
                    )
                    )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(scaffoldPadding)
        ) {
            AnimatedContent(targetState = isSearchBarVisible,
                transitionSpec = {
                    (expandHorizontally() with shrinkHorizontally())
                        .using(
                            SizeTransform(clip = false)
                        )
                }) {
                if (it){

                    TopAppBar(
                        backgroundColor = MaterialTheme.colors.secondaryVariant,
                        elevation = 0.dp,
                        modifier = Modifier
                            .shadow(
                                elevation = 8.dp, spotColor = Color.Gray,
                                ambientColor = MaterialTheme.colors.secondary
                            )
                            .fillMaxWidth()
                            .height(70.dp),
                    ) {
                        IconButton(onClick = {isSearchBarVisible = false}) {
                            Icon(
                                Icons.Default.ArrowBack,
                                null,
                                tint = MaterialTheme.colors.secondary
                            )
                        }
                        OutlinedTextField(
                            value = searchText,
                            textStyle = TextStyle(
                                fontSize = 22.sp
                            ),
                            onValueChange = {searchText = it},
                            placeholder = {
                                Text("Search...",
                                color = MaterialTheme.colors.secondary,
                                    fontSize = 22.sp
                                    ) },
                            colors = TextFieldDefaults.textFieldColors(
                                textColor = MaterialTheme.colors.onPrimary,
                                placeholderColor = MaterialTheme.colors.secondary,
                                backgroundColor = MaterialTheme.colors.secondaryVariant,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                cursorColor = MaterialTheme.colors.onPrimary

                            )
                        )
                    }
                }
                else{
                    TopAppBar(
                        elevation = 0.dp,
                        backgroundColor = Color.Transparent,
                        contentColor = Color.White,
                        modifier = Modifier
                            .padding(top = 15.dp)
                            .padding(horizontal = 15.dp),
                    ) {
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            Text(text = "Notes", style = MaterialTheme.typography.h1,
                                color = MaterialTheme.colors.onPrimary,
                                modifier = Modifier.clickable(
                                    interactionSource = interactionSource,
                                    indication = null,
                                    onClick = {
                                        scope.launch{
                                            listState.animateScrollToItem(0)
                                        }
                                    }
                                    )
                                )
                            Row(horizontalArrangement = Arrangement.SpaceEvenly){
                                ClassicButton(onClick = { isSearchBarVisible = true }, iconID = R.drawable.round_search_24)
                                Spacer(modifier = Modifier.width(10.dp))
                                ClassicButton(onClick = { openDialogue = true }, iconID = R.drawable.outline_info_24)
                            }
                        }
                    }}
            }

            var filteredList = listOf<Note>()

            LazyColumn(
                modifier = Modifier.padding(top= 10.dp),
                state = listState
                ){
                val searchKeyword = searchText.text
                filteredList = if(searchKeyword.isEmpty()){
                    noteList.reversed()
                }else{
                    val matchItems = mutableListOf<Note>()
                    for (n in noteList){
                        if (n.title.lowercase(Locale.getDefault())
                                .contains(searchKeyword.lowercase(Locale.getDefault()))
                        ) {
                            matchItems.add(n)
                        }
                    }
                    matchItems
                }

                this.itemsIndexed(filteredList){ n:Int,Note ->
                    NoteCard(title = Note.title,
                        color = cardColor(n+1),
                        deleteNote = { deleteNote.invoke(Note)
                        },
                    toNavigate = {
                        navController.navigate(Screen.NoteViewScreen.route+"/${Note.id}")})
                }
            }
        }

    }
}

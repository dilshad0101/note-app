package com.app.note.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "note_data")
data class Note(
    @PrimaryKey(autoGenerate = true )
    val id: Int,
    val title: String,
    val content: String
)


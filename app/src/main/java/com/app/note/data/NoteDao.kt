package com.app.note.data

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNote(note: Note)

    @Query("SELECT * FROM note_data ORDER BY id ASC")
    fun readAllData(): LiveData<List<Note>>

    @Delete
     suspend fun deleteNote(note: Note)

     @Query("DELETE FROM note_data")
     suspend fun deleteAllNotes()
}
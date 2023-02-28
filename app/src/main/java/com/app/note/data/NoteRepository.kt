package com.app.note.data

import androidx.lifecycle.LiveData

class NoteRepository(private val noteDao: NoteDao) {
    val readAllData: LiveData<List<Note>> = noteDao.readAllData()

    suspend fun addNote(note: Note){
        noteDao.addNote(note)
    }
    suspend fun deleteNote(note: Note){
        noteDao.deleteNote(note)
    }
    suspend fun deleteAllNote(){
        noteDao.deleteAllNotes()
    }
}
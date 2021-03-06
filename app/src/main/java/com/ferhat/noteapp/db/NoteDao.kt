package com.ferhat.noteapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ferhat.noteapp.model.Note

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(note:Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM notes ORDER BY id")
    fun getAllNotes() : LiveData<List<Note>>
}
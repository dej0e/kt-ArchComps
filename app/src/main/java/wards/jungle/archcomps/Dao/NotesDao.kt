package wards.jungle.archcomps.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import wards.jungle.archcomps.Model.Note

@Dao
abstract class NotesDao: BaseDao<Note>() {
    @Query("SELECT * FROM notes_table")
    abstract fun getAllNotes(): LiveData<List<Note>>

    @Query("DELETE FROM notes_table")
    abstract fun  deleteAllNotes()
}
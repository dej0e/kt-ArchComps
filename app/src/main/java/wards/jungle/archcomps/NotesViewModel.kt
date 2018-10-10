package wards.jungle.archcomps

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class NotesViewModel(application: Application) : AndroidViewModel(application) {
    private var noteRepository:NoteRepository = NoteRepository(application)
    var allNotes: LiveData<List<Note>> = noteRepository.allNotes

    fun insert(note:Note) {
        noteRepository.insert(note)
    }

    fun update(note:Note) {
        noteRepository.update(note)
    }

    fun delete(note:Note) {
        noteRepository.delete(note)
    }

    fun deleteAllNotes() {
        noteRepository.deleteAllNotes()
    }
}
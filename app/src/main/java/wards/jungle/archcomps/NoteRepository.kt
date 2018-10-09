package wards.jungle.archcomps

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import org.jetbrains.anko.doAsync

class NoteRepository {
    var noteDao: NotesDao?
    var allNotes: LiveData<List<Note>>

    constructor(application: Application){
        val notesDb:NotesDatabase? = NotesDatabase.getInstance(application)
        this.noteDao = notesDb?.notesDao()
        this.allNotes = noteDao!!.getAllNotes()
    }

    fun insert(note:Note) {
        doAsync { noteDao?.insert(note) }
    }

    fun update(note:Note) {
        doAsync { noteDao?.update(note) }
    }

    fun delete(note:Note) {
        doAsync { noteDao?.delete(note) }
    }

    fun deleteAllNotes() {
        doAsync { noteDao?.deleteAllNotes() }
    }
}

//class DoAsync<T>(val dao: T?, val handler: () -> Unit) : AsyncTask<Void, Void, Void>() {
//    override fun doInBackground(vararg params: Void?): Void? {
//        handler()
//        return null
//    }
//}
//
//class DoAsyncWithArgs<T, P>(val dao: T?, val handler: () -> Unit) : AsyncTask<P, Void, Void>() {
//    override fun doInBackground(vararg params: P): Void {
//        handler()
//        return null;
//    }
//}
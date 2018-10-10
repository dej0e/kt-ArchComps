package wards.jungle.archcomps

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import wards.jungle.archcomps.Dao.NotesDao

class NoteRepository(application: Application) {
    private var noteDao: NotesDao
    var allNotes: LiveData<List<Note>>

    init {
        val notesDb:NotesDatabase? = NotesDatabase.getInstance(application)
        this.noteDao = notesDb!!.notesDao()
        this.allNotes = noteDao.getAllNotes()
    }

    fun insert(note:Note) {
        DoAsyncWithArgs<NotesDao,Note>(noteDao) {
            dao, item -> dao.insert(item)
        }.execute(note)
    }

    fun update(note:Note) {
        DoAsyncWithArgs<NotesDao,Note>(noteDao) {
            dao, item -> dao.update(item)
        }.execute(note)    }

    fun delete(note:Note) {
        DoAsyncWithArgs<NotesDao,Note>(noteDao) {
            dao, item -> dao.delete(item)
        }.execute(note)    }

    fun deleteAllNotes() {
        DoAsync(noteDao) {
            dao -> dao.deleteAllNotes()
        }.execute()
    }
}

class DoAsync<T>(private val dao: T, val handler: (T) -> Unit) : AsyncTask<Void, Void, Int>() {

    override fun doInBackground(vararg params: Void?): Int {
        handler(dao)
        return 1
    }
}

class DoAsyncWithArgs<T, P>(private val dao: T, val handler: (T, P) -> Unit) : AsyncTask<P, Void, Int>() {
    override fun doInBackground(vararg params: P): Int {
        handler(dao, params[0])
        return 1
    }
}
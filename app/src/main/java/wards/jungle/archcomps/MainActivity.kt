package wards.jungle.archcomps

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

class MainActivity : AppCompatActivity() {
    companion object {
        val TAG = MainActivity::class.simpleName
    }
    lateinit var notesViewModel: NotesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel::class.java)
        notesViewModel.insert(Note("Testing 1", "Description 1", 10))
        notesViewModel.insert(Note("Testing 2", "Description 2", 10))
        var notes = notesViewModel.allNotes

        notes.value?.forEach { Log.d(TAG, it.title + " " +  it.description + " " + it.priority) }
    }
}

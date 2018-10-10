package wards.jungle.archcomps.Activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import wards.jungle.archcomps.Model.Note
import wards.jungle.archcomps.ViewModel.MainActivity.NotesViewModel
import wards.jungle.archcomps.R

class MainActivity : AppCompatActivity() {
    companion object {
        val TAG = MainActivity::class.simpleName
    }
    private lateinit var initializeButton: Button
    private lateinit var notesViewModel: NotesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel::class.java)

        initializeButton = findViewById(R.id.helloworld_button)
        initializeButton.setOnClickListener {
            for (i in 0..10) {
                notesViewModel.insert(Note(title = "Testing $i", description = "Description $i", priority = i))

            }
        }

        val nameObserver = Observer<List<Note>> { notes ->
            notes.forEach {
                val id = it.id
                Log.d(TAG, " $id | " + it.title + " | " +  it.description + " | " + it.priority) }
        }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        notesViewModel.allNotes.observe(this, nameObserver)

    }
}

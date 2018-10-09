package wards.jungle.archcomps

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.RoomDatabase.Callback
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = arrayOf(Note::class),
        version = 1,
        exportSchema = true)
abstract class NotesDatabase: RoomDatabase() {
    abstract fun notesDao(): NotesDao

    companion object {
        private var instance: NotesDatabase? = null
        private object Callback: RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
            }
        }
        fun getInstance(context: Context): NotesDatabase? {
            if(instance==null){
                synchronized(NotesDatabase::class) {
                    instance = Room.databaseBuilder(context.applicationContext, NotesDatabase::class.java, "notes.db")
                            .fallbackToDestructiveMigration()
                            .addCallback(Callback)
                            .build()
                }
            }
            return instance;
        }

        fun destroyInstance() {
            instance = null
        }
    }


}
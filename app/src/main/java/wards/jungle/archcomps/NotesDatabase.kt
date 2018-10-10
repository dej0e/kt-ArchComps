package wards.jungle.archcomps

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import wards.jungle.archcomps.Dao.NotesDao

@Database(entities = [Note::class],
        version = 1,
        exportSchema = true)
abstract class NotesDatabase: RoomDatabase() {
    abstract fun notesDao(): NotesDao

    companion object {
        val TAG = NotesDatabase::class.simpleName
        private var instance: NotesDatabase? = null
        private object Callback: RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                Log.d(TAG, "Instance Created")
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
            return instance
        }

        fun destroyInstance() {
            instance = null
        }
    }


}
package wards.jungle.archcomps.Database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import wards.jungle.archcomps.Dao.UserDao
import wards.jungle.archcomps.Model.User

@Database(entities = [User::class],
        version = 1,
        exportSchema = true)
abstract class ArchCompsDatabase: RoomDatabase() {
    abstract fun notesDao(): UserDao

    companion object {
        val TAG = ArchCompsDatabase::class.simpleName
        private var instance: ArchCompsDatabase? = null
        private object Callback: RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                Log.d(TAG, "Instance Created")
                super.onCreate(db)
            }
        }
        fun getInstance(context: Context): ArchCompsDatabase? {
            if(instance ==null){
                synchronized(ArchCompsDatabase::class) {
                    instance = Room.databaseBuilder(context.applicationContext, ArchCompsDatabase::class.java, "notes.db")
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
package wards.jungle.archcomps.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import wards.jungle.archcomps.Model.User

@Dao
abstract class UserDao: BaseDao<User>() {
    @Query("SELECT * FROM users_table")
    abstract fun getAllUsers(): LiveData<List<User>>

    @Query("DELETE FROM users_table")
    abstract fun  deleteAllUsers()
}
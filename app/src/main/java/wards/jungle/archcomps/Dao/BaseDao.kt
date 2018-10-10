package wards.jungle.archcomps.Dao

import androidx.room.*

abstract class BaseDao<in T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(type: T): Long

    @Update
    abstract fun update(type: T)

    @Delete
    abstract fun delete(type: T)
}
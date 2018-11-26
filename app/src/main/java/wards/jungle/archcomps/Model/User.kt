package wards.jungle.archcomps.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_table")
data class User (@ColumnInfo(name="email_id") var emailId:String) {
    @PrimaryKey(autoGenerate = true) var id:Int = 0
}
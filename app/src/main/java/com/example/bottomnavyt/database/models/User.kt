package com.example.bottomnavyt.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(

    var name: String?,
    var email: String?,
    var password: String?,
    var repassword: String?,
    var country: String?,
    var skill: String?,
    var add: String?,
    var phone: String?,
    var uname: String?

){

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}

package com.example.bottomnavyt.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Admin_table")
data class Admin(
    var Aname: String?,
    var Aemail: String?,
    var Apassword: String?,
    var Arepassword: String?
){

    @PrimaryKey(autoGenerate = true)
    var Aid: Int? = null
}

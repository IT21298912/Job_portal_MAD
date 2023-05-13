package com.example.mad2.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "admin_data_table")
data class Admin(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "admin_id")
    var id: Int?,
    @ColumnInfo(name = "admin_name")
    var name: String?,
    @ColumnInfo(name = "admin_email")
    var email:String,
    @ColumnInfo(name = "admin_password")
    var password:String
)
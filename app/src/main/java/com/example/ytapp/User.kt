package com.example.ytapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var name: String,
    var address: String,
    var country: String,
    var email: String,
    var phone: String,
    var skills: String
)


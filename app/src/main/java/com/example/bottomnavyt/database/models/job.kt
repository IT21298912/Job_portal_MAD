package com.example.bottomnavyt.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "job_table")
data class job(
    var jobhead:String,
    var uemail:String?


){

    @PrimaryKey(autoGenerate = true)
    var jid: Int? = null
}

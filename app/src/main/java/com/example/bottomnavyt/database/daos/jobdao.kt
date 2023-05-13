package com.example.bottomnavyt.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.bottomnavyt.database.models.User
import com.example.bottomnavyt.database.models.job


@Dao
interface jobdao {

    @Insert
    fun insert(jo: job)

    @Delete
    suspend fun delete(jo: job)

    @Query("SELECT * From job_table")   //to do is like table -Entity
    fun getAlljobs(): List<job>

    @Query("UPDATE job_table SET uemail=:email WHERE jid LIKE :jid")
    suspend fun applyjob(email:String, jid: Int?)

    @Query("SELECT * From job_table WHERE uemail=:email")   //to do is like table -Entity
    fun getAllappjobs(email: String): List<job>

    @Query("SELECT COUNT(*) FROM job_table")
    suspend fun getjobcount():Int
}
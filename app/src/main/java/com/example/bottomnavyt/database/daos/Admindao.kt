package com.example.bottomnavyt.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.bottomnavyt.database.models.Admin
import com.example.bottomnavyt.database.models.User


@Dao
interface Admindao {

    @Insert
    fun insert(admin:Admin)

    @Delete
    suspend fun delete(admin:Admin)

    @Query("SELECT * FROM Admin_table")
    fun getAll(): List<Admin>

    @Query("SELECT * FROM Admin_table WHERE Aemail = :email AND Apassword = :password")
    fun login(email: String, password: String): Admin

}
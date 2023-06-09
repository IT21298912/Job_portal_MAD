package com.example.mad2.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AdminDao {

    @Insert
    suspend fun insertAdmin(admin: Admin)

    @Update
    suspend fun updateAdmin(admin: Admin)

    @Delete
    suspend fun deleteAdmin(admin: Admin)

    @Query("SELECT * FROM admin_data_table")
    fun getAllAdmins(): LiveData<List<Admin>>


    @Query("SELECT * FROM admin_data_table WHERE admin_email = :email AND admin_password = :password")
    fun login(email: String, password: String): Admin
}
package com.example.bottomnavyt.database.repositeries

import com.example.bottomnavyt.database.AppDatabase
import com.example.bottomnavyt.database.models.Admin
import com.example.bottomnavyt.database.models.User

class Adminrepository(
    private val db: AppDatabase
) {

    suspend fun insert(admin: Admin)=db.getAdmindao().insert(admin)
    suspend fun delete(admin: Admin)=db.getAdmindao().delete(admin)
    fun getAll() =db.getAdmindao().getAll()
    fun login(email: String, password: String) = db.getAdmindao().login(email, password)


}
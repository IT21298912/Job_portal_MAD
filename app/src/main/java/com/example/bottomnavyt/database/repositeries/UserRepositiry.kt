package com.example.bottomnavyt.database.repositeries

import com.example.bottomnavyt.database.AppDatabase
import com.example.bottomnavyt.database.models.User

class UserRepositiry (
    private val db:AppDatabase


){

    suspend fun insert(user:User)=db.getuserDao().insert(user)
    suspend fun delete(user: User)=db.getuserDao().delete(user)

    fun login(email: String, password: String) = db.getuserDao().login(email, password)
    fun getuserdata(email: String)=db.getuserDao().getuserdata(email)

    suspend fun update(country :String,skill :String,address :String,phone:String,uname: String,email: String)=db.getuserDao().update(country, skill, address, phone, uname, email)





}
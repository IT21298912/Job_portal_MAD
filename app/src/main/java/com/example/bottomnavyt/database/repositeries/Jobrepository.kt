package com.example.bottomnavyt.database.repositeries

import com.example.bottomnavyt.database.AppDatabase
import com.example.bottomnavyt.database.models.job

class Jobrepository(
    private val db: AppDatabase

) {

    suspend fun insert(jo: job) = db.getjobdao().insert(jo)
    suspend fun delete(jo: job) = db.getjobdao().delete(jo)
    fun getAlljobs() =db.getjobdao().getAlljobs()
    suspend fun applyjob(email:String, jid: Int?) = db.getjobdao().applyjob(email,jid)
    fun getAllappjobs(email: String) = db.getjobdao().getAllappjobs(email)

    suspend fun getJobCount()=db.getjobdao().getJobCount()
}
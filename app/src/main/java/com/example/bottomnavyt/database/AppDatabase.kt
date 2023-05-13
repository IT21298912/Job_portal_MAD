package com.example.bottomnavyt.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bottomnavyt.Admin_login
import com.example.bottomnavyt.database.daos.UserDao
import com.example.bottomnavyt.database.daos.jobdao
import com.example.bottomnavyt.database.models.User
import com.example.bottomnavyt.database.models.job
import com.example.mad2.db.Admin
import com.example.mad2.db.AdminDao
import kotlinx.coroutines.CoroutineScope

@Database(entities = [User :: class,job::class, Admin::class], version = 6)
abstract class AppDatabase : RoomDatabase(){

    abstract fun getuserDao() : UserDao
    abstract fun getjobdao() : jobdao

    abstract fun getAdmindao() : AdminDao



    companion object{

        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase{

            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }

        }

    }

}
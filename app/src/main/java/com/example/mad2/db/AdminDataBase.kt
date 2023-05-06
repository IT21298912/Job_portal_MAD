package com.example.mad2.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Admin::class], version = 1, exportSchema = false)
abstract class AdminDataBase : RoomDatabase() {

    abstract fun adminDao(): AdminDao

    companion object {

        private var INSTANCE: AdminDataBase? = null
        fun getInstance(context: Context): AdminDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AdminDataBase::class.java,
                        "admin_data_database"
                    ).build()

                }
                return instance
            }
        }
    }
}
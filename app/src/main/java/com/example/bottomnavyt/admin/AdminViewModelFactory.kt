package com.example.mad2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bottomnavyt.admin.AdminViewModel
import com.example.mad2.db.AdminDao
import java.lang.IllegalArgumentException

class AdminViewModelFactory(
    private val dao: AdminDao
): ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AdminViewModel::class.java)){
            return AdminViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}
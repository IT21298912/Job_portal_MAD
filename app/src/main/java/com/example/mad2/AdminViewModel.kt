package com.example.mad2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mad2.db.Admin
import com.example.mad2.db.AdminDao
import kotlinx.coroutines.launch

class AdminViewModel(private val dao: AdminDao):ViewModel() {

    val admins = dao.getAllAdmins()

    fun insertAdmin(admin: Admin)  = viewModelScope.launch {
        dao.insertAdmin(admin)
    }

    fun updateAdmin(admin: Admin)  = viewModelScope.launch {
        dao.updateAdmin(admin)
    }

    fun deleteAdmin(admin: Admin)  = viewModelScope.launch {
        dao.deleteAdmin(admin)
    }





}
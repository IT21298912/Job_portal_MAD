package com.example.ytapp

import androidx.room.*

@Dao
interface UserDao {
    @Insert
    fun insert(user: User)

    @Query("SELECT * FROM users")
    fun getAll(): List<User>

    @Update
    fun update(user: User)

    @Query("SELECT * FROM users WHERE id = :userId")
    fun getUserById(userId: Int): User?

    @Delete
    fun delete(user: User)
}
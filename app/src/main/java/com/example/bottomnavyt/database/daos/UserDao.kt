package com.example.bottomnavyt.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.bottomnavyt.database.models.User

@Dao
interface UserDao {
    @Insert
    fun insert(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("SELECT * FROM user_table WHERE id = :userId")
    fun getUserById(userId: Int): User

    @Query("SELECT * FROM user_table WHERE email = :email AND password = :password")
    fun login(email: String, password: String): User

    @Query("SELECT * FROM user_table WHERE email = :email")
    fun getuserdata(email: String): User

    @Query("DELETE FROM user_table WHERE email = :email")
    suspend fun deleteUserByEmail(email: String)


    @Query("UPDATE user_table SET country=:country, skill=:skill, `add` =:address, phone=:phone, uname=:uname WHERE email LIKE :email")
    suspend fun update(country: String, skill: String, address: String, phone: String, uname: String, email: String)

}

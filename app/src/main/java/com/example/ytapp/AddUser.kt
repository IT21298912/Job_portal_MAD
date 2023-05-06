package com.example.ytapp

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddUser : AppCompatActivity() {

    var validate = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)

        //Views
        val etName = findViewById<EditText>(R.id.name)
        val etAddress = findViewById<EditText>(R.id.address)
        val etCountry = findViewById<EditText>(R.id.country)
        val etEmail = findViewById<EditText>(R.id.email)
        val etPhone = findViewById<EditText>(R.id.phoneNumber)
        val etSkills = findViewById<EditText>(R.id.skills)
        val btnAddUser = findViewById<Button>(R.id.addUserBtn)

        //Listeners
        btnAddUser.setOnClickListener {
            val name = etName.text.toString()
            val address = etAddress.text.toString()
            val country = etCountry.text.toString()
            val email = etEmail.text.toString()
            val phone = etPhone.text.toString()
            val skills = etSkills.text.toString()

            //Validate name
            if (name.isEmpty()) {
                etName.error = "Name is required"
                etName.requestFocus()
                return@setOnClickListener
            }
            //Validate address
            else if (address.isEmpty()) {
                etAddress.error = "Address is required"
                etAddress.requestFocus()
                return@setOnClickListener
            }
            //Validate country
            else if (country.isEmpty()) {
                etCountry.error = "Country is required"
                etCountry.requestFocus()
                return@setOnClickListener
            }
            //Validate email
            else if (email.isEmpty()) {
                etEmail.error = "Email is required"
                etEmail.requestFocus()
                return@setOnClickListener
            }
            else if (!email.contains("@")) {
                etEmail.error = "Invalid email"
                etEmail.requestFocus()
                return@setOnClickListener
            }
            //Validate phone
            else if (phone.isEmpty()) {
                etPhone.error = "Phone number is required"
                etPhone.requestFocus()
                return@setOnClickListener
            }
            else if (phone.length < 10) {
                etPhone.error = "Invalid phone number"
                etPhone.requestFocus()
                return@setOnClickListener
            }
            //Validate skills
            else if (skills.isEmpty()) {
                etSkills.error = "Skills are required"
                etSkills.requestFocus()
                return@setOnClickListener
            }

            //User model
            val user = User(name = name, address = address, country = country, email = email, phone = phone, skills = skills)

            GlobalScope.launch {

                try {
                    val userDao = MyDatabase.getInstance(applicationContext).userDao()
                    userDao.insert(user)
                    Log.e("User added successfully", "User added successfully")

                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(this@AddUser, "User added successfully", Toast.LENGTH_SHORT)
                            .show()
                        var intent = Intent(this@AddUser, AllUsers::class.java)
                        finish()
                        startActivity(intent)
                    }
                } catch (e: Exception) {
                    Handler(Looper.getMainLooper()).post {
                        Log.e("Error", e.toString())
                        Toast.makeText(this@AddUser, "Error", Toast.LENGTH_SHORT)
                            .show()
                        var intent = Intent(this@AddUser, AllUsers::class.java)
                        finish()
                        startActivity(intent)
                    }
                }
            }

            //Clear fields
            etName.setText("")
            etAddress.setText("")
            etCountry.setText("")
            etEmail.setText("")
            etPhone.setText("")
            etSkills.setText("")

        }
    }
}
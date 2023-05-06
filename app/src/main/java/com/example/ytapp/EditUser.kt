package com.example.ytapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EditUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_user)

        //Views
        val etName = findViewById<EditText>(R.id.name)
        val etAddress = findViewById<EditText>(R.id.address)
        val etCountry = findViewById<EditText>(R.id.country)
        val etEmail = findViewById<EditText>(R.id.email)
        val etPhone = findViewById<EditText>(R.id.phoneNumber)
        val etSkills = findViewById<EditText>(R.id.skills)
        val btnEdit = findViewById<Button>(R.id.editUserBtn)
        val btnDelete = findViewById<Button>(R.id.deleteUserBtn)
        val btnSave = findViewById<Button>(R.id.saveBtn)

        //Hide save button
        btnSave.visibility = View.GONE

        //Get data from intent
        val id = intent.getIntExtra("id", 0)
        val name = intent.getStringExtra("name")
        val address = intent.getStringExtra("address")
        val country = intent.getStringExtra("country")
        val email = intent.getStringExtra("email")
        val phone = intent.getStringExtra("phone")
        val skills = intent.getStringExtra("skills")

        Log.d("EditUser", "id: $id")

        //Set data to views
        etName.setText(name)
        etAddress.setText(address)
        etCountry.setText(country)
        etEmail.setText(email)
        etPhone.setText(phone)
        etSkills.setText(skills)

        //Disable fields
        etName.isEnabled = false
        etAddress.isEnabled = false
        etCountry.isEnabled = false
        etEmail.isEnabled = false
        etPhone.isEnabled = false
        etSkills.isEnabled = false

        //Listeners
        btnEdit.setOnClickListener {
            //Enable fields
            etName.isEnabled = true
            etAddress.isEnabled = true
            etCountry.isEnabled = true
            etEmail.isEnabled = true
            etPhone.isEnabled = true
            etSkills.isEnabled = true

            //Show save button
            btnEdit.visibility = View.GONE
            btnSave.visibility = View.VISIBLE
        }

        btnSave.setOnClickListener {

            //Get user data
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


            GlobalScope.launch {
                val userDao = MyDatabase.getInstance(applicationContext).userDao()
                val user = userDao.getUserById(id)

                if (user != null) {
                    // User found, update their name
                    if (name != null) {
                        user.name = name
                    }
                    if (address != null) {
                        user.address = address
                    }
                    if (country != null) {
                        user.country = country
                    }
                    if (email != null) {
                        user.email = email
                    }
                    if (phone != null) {
                        user.phone = phone
                    }
                    if (skills != null) {
                        user.skills = skills
                    }
                    Log.d("EditUser", "User: $user")
                    userDao.update(user)

                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(this@EditUser, "User updated", Toast.LENGTH_SHORT)
                            .show()
                        var Intent = Intent(this@EditUser, AllUsers::class.java)
                        finish()
                        startActivity(Intent)
                    }
                } else {
                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(this@EditUser, "Error", Toast.LENGTH_SHORT)
                            .show()
                        var Intent = Intent(this@EditUser, AllUsers::class.java)
                        finish()
                        startActivity(Intent)
                    }
                }
            }
        }

        btnDelete.setOnClickListener {

            //Delete warning
            val builder = AlertDialog.Builder(this@EditUser)
            builder.setTitle("Delete User")
            builder.setMessage("Are you sure you want to delete this user?")
            builder.setIcon(android.R.drawable.ic_dialog_alert)

            //Yes button
            builder.setPositiveButton("Yes") { dialogInterface, which ->

                GlobalScope.launch {
                    val userDao = MyDatabase.getInstance(applicationContext).userDao()
                    val user = userDao.getUserById(id)
                    if (user != null) {
                        userDao.delete(user)
                        Handler(Looper.getMainLooper()).post {
                            Toast.makeText(this@EditUser, "User updated", Toast.LENGTH_SHORT)
                                .show()
                            var intent = Intent(this@EditUser, AllUsers::class.java)
                            finish()
                            startActivity(intent)
                        }
                    } else {
                        // User not found
                        Handler(Looper.getMainLooper()).post {
                            Toast.makeText(this@EditUser, "Error", Toast.LENGTH_SHORT)
                                .show()
                            var intent = Intent(this@EditUser, AllUsers::class.java)
                            finish()
                            startActivity(intent)
                        }
                    }
                }
            }

            //No button
            builder.setNegativeButton("No") { dialogInterface, which ->
                Toast.makeText(this, "Delete cancelled", Toast.LENGTH_SHORT).show()
            }

            //Show dialog
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        }
    }
}
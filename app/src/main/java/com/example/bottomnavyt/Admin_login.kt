package com.example.bottomnavyt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.bottomnavyt.database.AppDatabase
import com.example.bottomnavyt.database.daos.Admindao
import com.example.bottomnavyt.database.daos.UserDao
import com.example.bottomnavyt.database.repositeries.Adminrepository
import com.example.bottomnavyt.database.repositeries.UserRepositiry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class Admin_login : AppCompatActivity() {

    private lateinit var appDatabase: AppDatabase
    private lateinit var admindao: Admindao
    private lateinit var repository: Adminrepository

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)


        appDatabase = AppDatabase.getDatabase(this)
        admindao = appDatabase.getAdmindao()
        repository = Adminrepository(appDatabase)

        emailEditText = findViewById(R.id.Alogemaildt)
        passwordEditText = findViewById(R.id.Alogpwdt)
        loginButton = findViewById(R.id.Alogrg)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                loginUser(email, password,repository)


            }


        }

    }

    private fun loginUser(email: String, password: String,repositiry: Adminrepository) {
        lifecycleScope.launch(Dispatchers.IO) {
            val user = repositiry.login(email, password)

            runOnUiThread {
                if (user != null) {
                    // Login success
                    // Save user session or navigate to next activity
//                    val fragment = Profile()
//                    val bundle = Bundle()
//                    bundle.putString("myKey", email)
//                    fragment.arguments = bundle





                    val Intent = Intent(this@Admin_login, selectmanage::class.java)
                    startActivity(Intent)




                    Toast.makeText(this@Admin_login, "Successfully login", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    // Login failed
                    // Show error message to the user
                    Toast.makeText(this@Admin_login, "login fail", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }
}
package com.example.bottomnavyt

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.bottomnavyt.database.AppDatabase
import com.example.bottomnavyt.database.daos.UserDao
import com.example.bottomnavyt.database.repositeries.UserRepositiry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class activity_login : AppCompatActivity() {

    private lateinit var appDatabase: AppDatabase
    private lateinit var userDao: UserDao
    private lateinit var repository: UserRepositiry

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    private lateinit var sharedPreferences: SharedPreferences
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        supportActionBar?.hide();
        setContentView(R.layout.activity_login)


        appDatabase = AppDatabase.getDatabase(this)
        userDao = appDatabase.getuserDao()
        repository = UserRepositiry(appDatabase)

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


    private fun loginUser(email: String, password: String,repositiry: UserRepositiry) {
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

                    callActivity()



                   // val Intent = Intent(this@activity_login, MainActivity::class.java)
                   // startActivity(Intent)




                    Toast.makeText(this@activity_login, "Successfully login", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    // Login failed
                    // Show error message to the user
                    Toast.makeText(this@activity_login, "login fail", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }


    private fun callActivity(){

        emailEditText = findViewById(R.id.Alogemaildt)
        val email = emailEditText.text.toString()

        val intent = Intent(this,MainActivity::class.java).also {
            it.putExtra("EXTRA_MESSAGE",email)
            startActivity(it)
        }


    }


}
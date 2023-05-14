package com.example.bottomnavyt

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.example.bottomnavyt.database.AppDatabase
import com.example.bottomnavyt.database.daos.UserDao
import com.example.bottomnavyt.database.repositeries.UserRepositiry
import com.example.bottomnavyt.validation.ValidationResult
import com.example.bottomnavyt.validation.modelval.FormData
import com.example.bottomnavyt.validation.modelval.logForm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class activity_login : AppCompatActivity() {

    private lateinit var appDatabase: AppDatabase      //late int variables
    private lateinit var userDao: UserDao
    private lateinit var repository: UserRepositiry

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    private lateinit var sharedPreferences: SharedPreferences
    private var count=0
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        supportActionBar?.hide();
        setContentView(R.layout.activity_login)


        appDatabase = AppDatabase.getDatabase(this)      //get db
        userDao = appDatabase.getuserDao()              //set dao
        repository = UserRepositiry(appDatabase)

        emailEditText = findViewById(R.id.Alogemaildt)       //get views
        passwordEditText = findViewById(R.id.Alogpwdt)
        loginButton = findViewById(R.id.Alogrg)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()                   //set to strings
            val password = passwordEditText.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                submit(it,repository,email,password)


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





    fun submit(v: View, repository: UserRepositiry,email: String,password: String){

        val lForm = logForm(
            passwordEditText.text.toString(),
            emailEditText.text.toString()

        )


        val emailvali = lForm.validateEmail()
        val passwrdvali = lForm.validatePassword()

        when (emailvali){

            is ValidationResult.Valid ->{
                count ++


            }
            is ValidationResult.Invalid ->{
                emailEditText.error = emailvali.errorMessage

            }
            is ValidationResult.Empty ->{
                emailEditText.error = emailvali.errorMessage

            }


        }

        when (passwrdvali){

            is ValidationResult.Valid ->{
                count ++


            }
            is ValidationResult.Invalid ->{
                passwordEditText.error = passwrdvali.errorMessage

            }
            is ValidationResult.Empty ->{
                passwordEditText.error = passwrdvali.errorMessage

            }


        }


        if(count==4){
            displayAlert("Success","You have successfully Login",email, password)

        }




    }


    fun displayAlert(title:String, message:String,email: String,password: String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("OK") { dialog, which ->
            // Do something when the "OK" button is clicked
            loginUser(email, password,repository)
        }
        val dialog = builder.create()
        dialog.show()
    }


    private fun callActivity(){           //pass the email as intent to fetch data to profile

        emailEditText = findViewById(R.id.Alogemaildt)
        val email = emailEditText.text.toString()

        val intent = Intent(this,MainActivity::class.java).also {
            it.putExtra("EXTRA_MESSAGE",email)
            startActivity(it)
        }


    }


}
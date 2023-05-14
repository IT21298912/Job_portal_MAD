package com.example.bottomnavyt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.example.bottomnavyt.database.AppDatabase
import com.example.bottomnavyt.database.repositeries.UserRepositiry
import com.example.bottomnavyt.validation.ValidationResult
import com.example.bottomnavyt.validation.modelval.logForm
import com.example.mad2.db.AdminDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class Admin_login : AppCompatActivity() {

    private lateinit var appDatabase: AppDatabase
    private lateinit var admindao: AdminDao


    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private var count=0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)


        appDatabase = AppDatabase.getDatabase(this)
        admindao = appDatabase.getAdmindao()


        emailEditText = findViewById(R.id.Alogemaildt)
        passwordEditText = findViewById(R.id.Alogpwdt)
        loginButton = findViewById(R.id.Alogrg)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
               // loginUser(email, password,admindao)

                submit(it,admindao,email, password)


            }


        }

    }

    private fun loginUser(email: String, password: String,dao: AdminDao) {
        lifecycleScope.launch(Dispatchers.IO) {
            val user = dao.login(email, password)

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


    fun submit(v: View, dao: AdminDao, email: String, password: String){

        val lForm = logForm(
            passwordEditText.text.toString(),
            emailEditText.text.toString()

        )



        val passwrdvali = lForm.validatePassword()


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


        if(count==1){
            displayAlert("Success","You have successfully Login",email, password,dao)
          //  loginUser(email, password,dao)
        }




    }


    fun displayAlert(title:String, message:String,email: String,password: String,dao: AdminDao){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("OK") { dialog, which ->
            // Do something when the "OK" button is clicked
            loginUser(email, password,dao)
        }
        val dialog = builder.create()
        dialog.show()
    }
}
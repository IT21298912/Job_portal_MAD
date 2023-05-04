package com.example.bottomnavyt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.bottomnavyt.database.AppDatabase
import com.example.bottomnavyt.database.daos.UserDao
import com.example.bottomnavyt.database.models.User
import com.example.bottomnavyt.database.repositeries.UserRepositiry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class activity_signup : AppCompatActivity() {

    private lateinit var appDatabase: AppDatabase
    private lateinit var userDao: UserDao
    private lateinit var repository: UserRepositiry



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        supportActionBar?.hide();
        setContentView(R.layout.activity_signup)


        appDatabase = AppDatabase.getDatabase(this)
        userDao = appDatabase.getuserDao()
        repository = UserRepositiry(appDatabase)

        val signupbtn = findViewById<Button>(R.id.Asigbtn)

        signupbtn.setOnClickListener{

            writeData(repository)


        }









    }


    private fun writeData(repository:UserRepositiry){

        val nameEditText = findViewById<EditText>(R.id.Anamedt)
        val emailEditText = findViewById<EditText>(R.id.Aemaiedt)
        val passwordEditText = findViewById<EditText>(R.id.Apassedt)
        val repasswordEditText = findViewById<EditText>(R.id.repassedt)


        val name = nameEditText.text.toString()
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()
        val repassword = repasswordEditText.text.toString()

        if(name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() &&repassword.isNotEmpty()    ) {



            CoroutineScope(Dispatchers.IO).launch {        //call the methods inside couroutine

                repository.insert(User(name, email, password, repassword,null,null,null,null,null))
            }








            Toast.makeText(this@activity_signup,"Successfully inserted",Toast.LENGTH_SHORT).show()
        }else{

            Toast.makeText(this@activity_signup,"PLease Enter Data", Toast.LENGTH_SHORT).show()
        }




    }



}
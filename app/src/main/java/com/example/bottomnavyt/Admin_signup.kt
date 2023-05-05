package com.example.bottomnavyt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.bottomnavyt.database.AppDatabase
import com.example.bottomnavyt.database.daos.Admindao
import com.example.bottomnavyt.database.daos.UserDao
import com.example.bottomnavyt.database.models.Admin
import com.example.bottomnavyt.database.models.User
import com.example.bottomnavyt.database.repositeries.Adminrepository
import com.example.bottomnavyt.database.repositeries.UserRepositiry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Admin_signup : AppCompatActivity() {



    private lateinit var appDatabase: AppDatabase
    private lateinit var admindao: Admindao
    private lateinit var repository: Adminrepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_signup)


        appDatabase = AppDatabase.getDatabase(this)
        admindao = appDatabase.getAdmindao()
        repository = Adminrepository(appDatabase)


        val signupbtn = findViewById<Button>(R.id.Asigbtn)

        signupbtn.setOnClickListener{

            writeData(repository)


        }


    }

    private fun writeData(repository:Adminrepository){

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

                repository.insert(Admin(name, email, password, repassword))
            }








            Toast.makeText(this@Admin_signup,"Successfully inserted", Toast.LENGTH_SHORT).show()
        }else{

            Toast.makeText(this@Admin_signup,"PLease Enter Data", Toast.LENGTH_SHORT).show()
        }




    }

}
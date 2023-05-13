package com.example.bottomnavyt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.bottomnavyt.database.AppDatabase
import com.example.mad2.db.Admin
import com.example.mad2.db.AdminDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Admin_signup : AppCompatActivity() {



    private lateinit var appDatabase: AppDatabase
    private lateinit var admindao: AdminDao


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_signup)


        appDatabase = AppDatabase.getDatabase(this)
        admindao = appDatabase.getAdmindao()


        val signupbtn = findViewById<Button>(R.id.Asigbtn)

        signupbtn.setOnClickListener{

            writeData(admindao)


        }


    }

    private fun writeData(dao: AdminDao){

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

                dao.insertAdmin(Admin(null, name, email, password))
            }








            Toast.makeText(this@Admin_signup,"Successfully inserted", Toast.LENGTH_SHORT).show()
        }else{

            Toast.makeText(this@Admin_signup,"PLease Enter Data", Toast.LENGTH_SHORT).show()
        }




    }

}
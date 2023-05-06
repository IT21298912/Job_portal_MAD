package com.example.bottomnavyt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.bottomnavyt.database.AppDatabase
import com.example.bottomnavyt.database.daos.UserDao
import com.example.bottomnavyt.database.models.User
import com.example.bottomnavyt.database.repositeries.UserRepositiry
import com.example.bottomnavyt.validation.ValidationResult
import com.example.bottomnavyt.validation.modelval.FormData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class activity_signup : AppCompatActivity() {

    private lateinit var appDatabase: AppDatabase
    private lateinit var userDao: UserDao
    private lateinit var repository: UserRepositiry

    lateinit var sname:EditText
    lateinit var semail:EditText
    lateinit var passwrd:EditText
    lateinit var repasswrd:EditText
    private var count=0





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

            submit(it,repository)

            //writeData(repository)


        }

        semail = findViewById(R.id.Aemaiedt)
        sname = findViewById(R.id.Anamedt)
        passwrd = findViewById(R.id.Apassedt)
        repasswrd = findViewById(R.id.repassedt)





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


    fun displayAlert(title:String, message:String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("OK") { dialog, which ->
            // Do something when the "OK" button is clicked
        }
        val dialog = builder.create()
        dialog.show()
    }



//    semail = findViewById(R.id.Aemaiedt)
//    sname = findViewById(R.id.Anamedt)
//    passwrd = findViewById(R.id.Apassedt)
//    repasswrd = findViewById(R.id.repassedt)
    fun submit(v: View,repository: UserRepositiry){

        val myForm = FormData(
            semail.text.toString(),
            sname.text.toString(),
            passwrd.text.toString(),
            repasswrd.text.toString()


        )

    val emailvali = myForm.validateEmail()
    val snamevali = myForm.validatename()
    val passwrdvali = myForm.validatePassword()
    val repasswrdvali = myForm.validaterePassword()


    when (emailvali){

        is ValidationResult.Valid ->{
            count ++


        }
        is ValidationResult.Invalid ->{
            semail.error = emailvali.errorMessage

        }
        is ValidationResult.Empty ->{
            semail.error = emailvali.errorMessage

        }


    }

        when (snamevali){

        is ValidationResult.Valid ->{
            count ++


        }
        is ValidationResult.Invalid ->{
            sname.error = snamevali.errorMessage

        }
        is ValidationResult.Empty ->{
            sname.error = snamevali.errorMessage

        }


    }

        when (passwrdvali){

        is ValidationResult.Valid ->{
            count ++


        }
        is ValidationResult.Invalid ->{
            passwrd.error = passwrdvali.errorMessage

        }
        is ValidationResult.Empty ->{
            passwrd.error = passwrdvali.errorMessage

        }


    }

        when (repasswrdvali){

        is ValidationResult.Valid ->{
            count ++


        }
        is ValidationResult.Invalid ->{

            repasswrd.error = repasswrdvali.errorMessage

        }
        is ValidationResult.Empty ->{
            repasswrd.error = repasswrdvali.errorMessage


        }


    }


    if(count==4){
        displayAlert("Success","You have successfully registered")
        writeData(repository)
    }







}








    }
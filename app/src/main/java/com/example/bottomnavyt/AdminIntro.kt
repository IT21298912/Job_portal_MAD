package com.example.bottomnavyt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class AdminIntro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_intro)




        val loginpagebtn = findViewById<Button>(R.id.Alogbtn)

        loginpagebtn.setOnClickListener(){
            val Intent = Intent(this,Admin_login::class.java)
            startActivity(Intent)

        }

        val signup = findViewById<Button>(R.id.Aregbtn)

        signup.setOnClickListener(){
            val Intent = Intent(this,Admin_signup::class.java)
            startActivity(Intent)

        }
    }
}
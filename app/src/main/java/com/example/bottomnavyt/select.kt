package com.example.bottomnavyt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button

class select : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        supportActionBar?.hide();
        setContentView(R.layout.activity_select)


        val loginpagebtn = findViewById<Button>(R.id.Alogbtn)

        loginpagebtn.setOnClickListener(){
            val Intent = Intent(this,activity_login::class.java)
            startActivity(Intent)

        }


        val registerpagebtn = findViewById<Button>(R.id.Aregbtn)

        registerpagebtn.setOnClickListener(){
            val Intent = Intent(this,activity_signup::class.java)
            startActivity(Intent)

        }
    }
}
package com.example.bottomnavyt

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView

class activity_main : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        supportActionBar?.hide();
        setContentView(R.layout.activity_main2)

        val selectebtn = findViewById<TextView>(R.id.button1)

        selectebtn.setOnClickListener(){
            val Intent = Intent(this,select::class.java)
            startActivity(Intent)

        }


        val Admin = findViewById<Button>(R.id.admin)

        Admin.setOnClickListener {

            val Intent = Intent(this,AdminIntro::class.java)
            startActivity(Intent)


        }
    }
}
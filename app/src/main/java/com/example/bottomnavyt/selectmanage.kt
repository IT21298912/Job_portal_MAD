package com.example.bottomnavyt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.bottomnavyt.admin.AdminActivity

class selectmanage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selectmanage)


        val adminman = findViewById<Button>(R.id.Aman)

        adminman.setOnClickListener(){
            val Intent = Intent(this,AdminActivity::class.java)
            startActivity(Intent)

        }


        val jobm = findViewById<Button>(R.id.Jman)

        jobm.setOnClickListener(){
            val Intent = Intent(this,jobmanagement::class.java)
            startActivity(Intent)

        }

        val uman = findViewById<Button>(R.id.uman)

        uman.setOnClickListener(){
            val Intent = Intent(this,com.example.bottomnavyt.usermanagement.AllUsers::class.java)
            startActivity(Intent)

        }


    }
}
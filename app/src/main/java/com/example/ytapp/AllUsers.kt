package com.example.ytapp

import UserAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AllUsers : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_users)

        //Views
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val recyclerview = findViewById<RecyclerView>(R.id.recycler_view)

        //Listeners
        btnAdd.setOnClickListener {
            var intent = Intent(this, AddUser::class.java)
            startActivity(intent)
        }

        GlobalScope.launch {
            //Get all users from database
            val userDao = MyDatabase.getInstance(application).userDao()
            val users = userDao.getAll()
            Log.d("AllUsers", users.toString())
            // this creates a vertical layout Manager
            recyclerview.layoutManager = LinearLayoutManager(this@AllUsers)

            // This will pass the ArrayList to our Adapter
            val adapter = UserAdapter(users as ArrayList<User>)

            // Setting the Adapter with the recyclerview
            recyclerview.adapter = adapter
        }
    }
}
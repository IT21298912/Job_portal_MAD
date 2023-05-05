package com.example.bottomnavyt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavyt.adapters.jobAdapter
import com.example.bottomnavyt.database.AppDatabase
import com.example.bottomnavyt.database.models.job
import com.example.bottomnavyt.database.repositeries.Jobrepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class jobmanagement : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jobmanagement)

        val repository = Jobrepository(AppDatabase.getDatabase(this))


        val recyclerView: RecyclerView = findViewById(R.id.jobrecle)
        val ui = this
        val adapter = jobAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(ui)     //bind the recycle view


        val addadmin = findViewById<Button>(R.id.addjobM)
        addadmin.setOnClickListener {
            displayDialog(repository, adapter)
        }

        CoroutineScope(Dispatchers.IO).launch {
            val data = repository.getAlljobs()
            adapter.setData(data, ui)
        }


    }

    //create dialog box
    fun displayDialog(repository: Jobrepository, adapter: jobAdapter) {

        // Create a new instance of AlertDialog.Builder
        val builder = AlertDialog.Builder(this)

        // Set the alert dialog title and message
        builder.setTitle("Enter New job:")
        builder.setMessage("Enter the job name below:")


        // Create an EditText input field
        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)


        // Set the positive button action
        builder.setPositiveButton("OK") { dialog, which ->
            // Get the input text and display a Toast message
            val item = input.text.toString()

            CoroutineScope(Dispatchers.IO).launch {

                repository.insert(job(item,"Not_Apply"))   //insert it to db
                val data = repository.getAlljobs() //rendering the recycle view

                runOnUiThread{

                    adapter.setData(data,this@jobmanagement)

                }





            }
        }
        // Set the negative button action
        builder.setNegativeButton("Cancel") { dialog, which ->
            dialog.cancel()
        }


        // Create and show the alert dialog
        val alertDialog = builder.create()
        alertDialog.show()



    }


}
package com.example.bottomnavyt.admin


import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavyt.R
import com.example.bottomnavyt.database.AppDatabase
import com.example.mad2.AdminViewModelFactory
import com.example.mad2.db.Admin


class AdminActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var clearButton: Button

    private lateinit var viewModel: AdminViewModel
    private lateinit var adminRecyclerView: RecyclerView
    private lateinit var adapter: AdminRecyclerViewAdapter

    private lateinit var selectedAdmin: Admin
    private  var isListItemClicked = false

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maina)

        nameEditText = findViewById(R.id.etName)
        emailEditText = findViewById(R.id.etEmail)
        passwordEditText = findViewById(R.id.etpassword)
        saveButton = findViewById(R.id.btnSave)
        clearButton = findViewById(R.id.btnClear)
        adminRecyclerView = findViewById(R.id.rvAdmin)

        val appDatabase =AppDatabase.getDatabase(this)

        val dao = appDatabase.getAdmindao()
        val factory = AdminViewModelFactory(dao)
        viewModel = ViewModelProvider(this, factory).get(AdminViewModel::class.java)

        saveButton.setOnClickListener {

            if (nameEditText.text.isEmpty()){

                nameEditText.error = " please Enter Name"
            }
            else if (emailEditText . text . isEmpty ()){

                emailEditText.error = " please Enter email"

            }
            else if (
                passwordEditText.text.isEmpty()){
                passwordEditText.error = " please Enter password"
            }
            else{
                if (isListItemClicked) {

                    updateAdminData()
                    clearInput()

                } else {
                    saveAdminData()
                    clearInput()
                }

            }


        }

        clearButton.setOnClickListener {
            if(isListItemClicked){
                deleteAdminData()
                clearInput()

            }else {
                clearInput()
            }
        }

        initRecyclerView()
    }

    private fun saveAdminData(){
        viewModel.insertAdmin(
            Admin(
                0,
                nameEditText.text.toString(),
                emailEditText.text.toString(),
                passwordEditText.text.toString()
            )
        )
        Toast.makeText(
            this,
            "Successfully Added!!",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun updateAdminData(){
        viewModel.updateAdmin(
            Admin(
                selectedAdmin.id,
                nameEditText.text.toString(),
                emailEditText.text.toString(),
                passwordEditText.text.toString()
            )
        )
        Toast.makeText(
            this,
            "Successfully Updated!!",
            Toast.LENGTH_LONG
        ).show()
        saveButton.text = "Save"
        clearButton.text = "Clear"
        isListItemClicked = false
    }

    private fun deleteAdminData(){
        viewModel.deleteAdmin(
            Admin(
                selectedAdmin.id,
                nameEditText.text.toString(),
                emailEditText.text.toString(),
                passwordEditText.text.toString()
            )
        )
        Toast.makeText(
            this,
            "Successfully Deleted!!",
            Toast.LENGTH_LONG
        ).show()
        saveButton.text = "Save"
        clearButton.text = "Clear"
        isListItemClicked = false
    }



    private fun clearInput(){
        nameEditText.setText("")
        emailEditText.setText("")
        passwordEditText.setText("")
    }

    private fun initRecyclerView(){
        adminRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = AdminRecyclerViewAdapter{
                selectedItem:Admin -> listItemClicked(selectedItem)
        }
        adminRecyclerView.adapter = adapter

        displayAdminsList()
    }

    private fun  displayAdminsList() {
        viewModel.admins.observe(this) {
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        }
    }

    private fun listItemClicked(admin: Admin){
//          Toast.makeText(
//              this,
//              "Admin ${admin.name}",
//          Toast.LENGTH_LONG
//          ).show()

        selectedAdmin = admin
        saveButton.text = "Update"
        clearButton.text = "Delete"
        isListItemClicked = true
        nameEditText.setText(selectedAdmin.name)
        emailEditText.setText(selectedAdmin.email)
        passwordEditText.setText(selectedAdmin.password)

    }


}
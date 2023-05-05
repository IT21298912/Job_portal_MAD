package com.example.bottomnavyt.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavyt.R
import com.example.bottomnavyt.database.AppDatabase
import com.example.bottomnavyt.database.models.Admin
import com.example.bottomnavyt.database.models.job
import com.example.bottomnavyt.database.repositeries.Adminrepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


//class TodoAdapter:RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

class Adminadapter:RecyclerView.Adapter<Adminadapter.ViewHolder>() {


    lateinit var data: List<Admin>
    lateinit var context: Context

    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {        //creating view holder
        val admincheck: CheckBox
        val ivDelete: Button

        init {
            admincheck = view.findViewById(R.id.appjoc2)           //oncreate view we can access findViewById directly but adapter we cant
            ivDelete = view.findViewById(R.id.deladmin)        //we use init for this, acces for every resources
        }

    }


    fun setData(data: List<Admin>, context: Context) {

        this.data = data    //set to lateint variable upper
        this.context = context
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.admin_item,parent,false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return data.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.admincheck.text = data[position].Aname        //set the text position means return count text in check box

        holder.ivDelete.setOnClickListener {


            if (holder.admincheck.isChecked) {
                val repository = Adminrepository(AppDatabase.getDatabase(context))
                holder.admincheck.isChecked=false
                //holder.ivDelete.setImageResource(R.drawable.delete_icon_selected)

                CoroutineScope(Dispatchers.IO).launch {
                    repository.delete(data[position])      //delete the data
                    val data =
                        repository.getAll()    //get the latest update without deleted data


                    withContext(Dispatchers.Main) {   //switch to the main
                        setData(data, context)
                        //holder.ivDelete.setImageResource(R.drawable.delete_icon)


                    }


                }


            } else {

                Toast.makeText(context, "Cannot delete unmarked Todo items", Toast.LENGTH_LONG)
                    .show()


            }

        }

    }






}
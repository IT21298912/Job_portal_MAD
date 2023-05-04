package com.example.bottomnavyt

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavyt.database.AppDatabase
import com.example.bottomnavyt.database.models.job
import com.example.bottomnavyt.database.repositeries.Jobrepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Applyadapter(private val email:String): RecyclerView.Adapter<Applyadapter.ViewHolder>()  {

    lateinit var data: List<job>
    lateinit var context: Context


    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {






        val jobcheck: CheckBox
        val del: Button

        init {

            jobcheck = view.findViewById(R.id.appjoc2)
            del = view.findViewById(R.id.deletbtn)

        }


    }


    fun setData(data: List<job>, context: Context) {

        this.data = data    //set to lateint variable upper

        this.context = context
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Applyadapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.apply_item,parent,false)
        return Applyadapter.ViewHolder(view)

    }


    override fun getItemCount(): Int {
        return data.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {




        holder.jobcheck.text = data[position].jobhead

        val currentJob = data[position]

        holder.del.setOnClickListener{

            if (holder.jobcheck.isChecked) {


                val  repository = Jobrepository(AppDatabase.getDatabase(context))
                holder.jobcheck.isChecked=false


                CoroutineScope(Dispatchers.IO).launch {

                    repository.applyjob("Not_Apply",currentJob.jid)
                    val data = repository.getAllappjobs(email)


                    withContext(Dispatchers.Main) {   //switch to the main
                        setData(data, context)



                    }


                }



            }else{

                Toast.makeText(context, "Cannot Apply unmarked job items", Toast.LENGTH_LONG)
                    .show()



            }



        }
    }






}
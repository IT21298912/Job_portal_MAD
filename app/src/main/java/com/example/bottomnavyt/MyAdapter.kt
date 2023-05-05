package com.example.bottomnavyt

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavyt.database.AppDatabase
import com.example.bottomnavyt.database.models.job
import com.example.bottomnavyt.database.repositeries.Jobrepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


//class TodoAdapter:RecyclerView.Adapter<TodoAdapter.ViewHolder>() {
class MyAdapter(private val email:String):RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    lateinit var data: List<job>
    lateinit var context: Context






    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {






        val jobcheck: CheckBox
        val apply: Button
        val ivDelete:Button

        init {

            jobcheck = view.findViewById(R.id.appjoc2)
            apply = view.findViewById(R.id.appbtn)
            ivDelete = view.findViewById(R.id.wishbtn)

        }


    }



    fun setData(data: List<job>, context: Context) {

        this.data = data    //set to lateint variable upper

        this.context = context
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {




        holder.jobcheck.text = data[position].jobhead

        val currentJob = data[position]

        holder.apply.setOnClickListener{

            if (holder.jobcheck.isChecked) {


                val  repository = Jobrepository(AppDatabase.getDatabase(context))
                holder.jobcheck.isChecked=false


                CoroutineScope(Dispatchers.IO).launch {

                    repository.applyjob(email,currentJob.jid)


                }



            }else{

                Toast.makeText(context, "Cannot Apply unmarked job items", Toast.LENGTH_LONG)
                    .show()



            }



        }

        holder.ivDelete.setOnClickListener {

            if (holder.jobcheck.isChecked) {


                val  repository = Jobrepository(AppDatabase.getDatabase(context))
                holder.jobcheck.isChecked=false


                CoroutineScope(Dispatchers.IO).launch {

                    repository.delete(data[position])
                    val data = repository.getAlljobs()


                    withContext(Dispatchers.Main) {   //switch to the main
                        setData(data, context)



                    }


                }



            }else{

                Toast.makeText(context, "Cannot delete unmarked job items", Toast.LENGTH_LONG)
                    .show()



            }




        }



    }


}
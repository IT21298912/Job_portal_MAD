package com.example.mad2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mad2.db.Admin
import android.widget.ExpandableListView.OnChildClickListener

class AdminRecyclerViewAdapter(
    private val clickListener:(Admin)->Unit
):RecyclerView.Adapter<AdminViewHolder>(){

    private val adminList = java.util.ArrayList<Admin>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.list_item,parent,false)
        return AdminViewHolder(listItem)
    }

    override fun getItemCount(): Int {
        return adminList.size
    }

    override fun onBindViewHolder(holder: AdminViewHolder, position: Int) {
        holder.bind(adminList[position],clickListener)
    }

    fun setList(admins:List<Admin>){
        adminList.clear()
        adminList.addAll(admins)
    }

}


class AdminViewHolder(private val view:View):RecyclerView.ViewHolder(view){
    fun bind(admin: Admin,clickListener:(Admin)->Unit ){
        val nameTextView = view.findViewById<TextView>(R.id.tvName)
        val emailTextView = view.findViewById<TextView>(R.id.tvEmail)
        val passwordTextView = view.findViewById<TextView>(R.id.tvPassword)
        nameTextView.text = admin.name
        emailTextView.text = admin.email
        passwordTextView.text = admin.password
        view.setOnClickListener {
            clickListener(admin)
        }
    }
}


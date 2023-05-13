package com.example.bottomnavyt.usermanagement

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavyt.R
import com.example.bottomnavyt.database.models.User


class UserAdapter(Users: ArrayList<User>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    val data = Users

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_card_view, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = data[position]
// sets the text to the textview from our itemHolder class
        holder.textView.text = item.name?.trim()?.dropLast(1)

        holder.card.setOnClickListener(View.OnClickListener {
            //Go to edit item page
            val intent = Intent(holder.itemView.context, EditUser::class.java)
            intent.putExtra("id", item.id)
            intent.putExtra("name", item.name)
            intent.putExtra("email", item.email)
            intent.putExtra("country", item.country)
            intent.putExtra("phone", item.phone)
            intent.putExtra("address", item.add)
            intent.putExtra("skills", item.skill)
            holder.itemView.context.startActivity(intent)
        })


    }


        // return the number of the items in the list
    override fun getItemCount(): Int {
        return data.size
    }



    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val textView: TextView = itemView.findViewById(R.id.textView)
        val card: View = itemView.findViewById(R.id.card)
    }

}

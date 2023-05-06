import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ytapp.EditUser
import com.example.ytapp.R
import com.example.ytapp.User


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

        val Item = data[position]
        // sets the text to the textview from our itemHolder class
        holder.textView.text = Item.name.trim().dropLast(1)

        holder.card.setOnClickListener(View.OnClickListener {
            //Go to edit item page
            val intent = Intent(holder.itemView.context, EditUser::class.java)
            intent.putExtra("id", Item.id);
            intent.putExtra("name", Item.name);
            intent.putExtra("email", Item.email);
            intent.putExtra("country", Item.country);
            intent.putExtra("phone", Item.phone);
            intent.putExtra("address", Item.address);
            intent.putExtra("skills", Item.skills);
            holder.itemView.context.startActivity(intent);
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

import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavyt.MyAdapter
import com.example.bottomnavyt.R
import com.example.bottomnavyt.database.AppDatabase
import com.example.bottomnavyt.database.models.job
import com.example.bottomnavyt.database.repositeries.Jobrepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Home : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Home().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Any additional view initialization or logic can be added here

       // val repository = TodoRepository(TodoDatabase.getInstance(this))

        // Retrieve data from the Intent
        val myVariable = arguments?.getString("myKey")

        val last=myVariable.toString()

        Log.d("Tag", last)



        val fragmentContext = requireContext()
        val repository = Jobrepository(AppDatabase.getDatabase(fragmentContext))

        val recyclerView: RecyclerView = view.findViewById(R.id.joblistrec)
        val ui = fragmentContext
        val adapter = MyAdapter(last)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(ui)

        val addjob = view.findViewById<Button>(R.id.addjob)


        addjob.setOnClickListener {
            displayDialog(repository, adapter,ui)



        }

        CoroutineScope(Dispatchers.IO).launch {
            val data = repository.getAlljobs()
            adapter.setData(data, ui)
        }



    }


    fun displayDialog(repository:Jobrepository, adapter:MyAdapter,context:Context) {

        val builder = AlertDialog.Builder(context)


        builder.setTitle("Enter New job item:")
        builder.setMessage("Enter the job item below:")

        val input = EditText(context)
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)


        builder.setPositiveButton("OK") { dialog, which ->

            val item = input.text.toString()


            CoroutineScope(Dispatchers.IO).launch {

                repository.insert(job(item,null))   //insert it to db
                val data = repository.getAlljobs() //rendering the recycle view

                requireActivity().runOnUiThread{

                    adapter.setData(data,requireContext())

                }





            }






        }

        builder.setNegativeButton("Cancel") { dialog, which ->
            dialog.cancel()
        }

        val alertDialog = builder.create()
        alertDialog.show()



    }


}

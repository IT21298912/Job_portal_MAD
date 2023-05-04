package com.example.bottomnavyt

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

import androidx.fragment.app.Fragment
import com.example.bottomnavyt.database.AppDatabase
import com.example.bottomnavyt.database.models.User
import com.example.bottomnavyt.database.repositeries.UserRepositiry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


private lateinit var name:EditText
private lateinit var email:EditText
private lateinit var uname:EditText
private lateinit var address:EditText
private lateinit var skill:EditText
private lateinit var country:EditText
private lateinit var phone:EditText

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Profile.newInstance] factory method to
 * create an instance of this fragment.
 */
class Profile : Fragment() {
    // TODO: Rename and change types of parameters
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
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_profile, container, false)


        val fragmentContext = requireContext()

        val repository = UserRepositiry(AppDatabase.getDatabase(fragmentContext))







        // Retrieve data from the Intent
        val myVariable = arguments?.getString("myKey")



        //get views
        name= view.findViewById(R.id.nameedt)
        email= view.findViewById(R.id.emailedtt)
        uname= view.findViewById(R.id.uname)
        address= view.findViewById(R.id.addedt)
        skill= view.findViewById(R.id.skiledt)
        country= view.findViewById(R.id.countryedt)
        phone= view.findViewById(R.id.phedt)
        val updatebtn= view.findViewById<Button>(R.id.upbtn)
        val delbtn= view.findViewById<Button>(R.id.delbtn)



        if(myVariable==null){
            print("nulllll")
        }

        val last=myVariable.toString()


        //email.setText(last)


        //get name from DB pasing the email

        GlobalScope.launch {


            var user: User = repository.getuserdata(last)
            name.setText(user.name)
            email.setText(user.email)
            uname.setText(user.uname)
            address.setText(user.add)
            skill.setText(user.skill)
            country.setText(user.country)
            phone.setText(user.phone)

        }


        updatebtn.setOnClickListener{

            //set to Strings
            val emailstr= email.text.toString()
            val nameStr= name.text.toString()
            val unamestr= uname.text.toString()
            val addstr= address.text.toString()
            val skillstr= skill.text.toString()
            val countrystr= country.text.toString()
            val phonestr= phone.text.toString()



            updateUdetail(repository,last,countrystr,skillstr,addstr,phonestr,unamestr)

        }





        return view
    }


    private fun updateUdetail(repositiry: UserRepositiry,email:String, country:String,skill:String,add:String,phone:String,uname:String){

        GlobalScope.launch(Dispatchers.IO) {

           repositiry.update(country, skill, add, phone, uname, email)


        }




    }





    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Profile.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Profile().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
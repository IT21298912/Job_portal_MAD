package com.example.bottomnavyt

import Home
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavyt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val message = intent.getStringExtra("EXTRA_MESSAGE")
        replaceFragment(Home())

        binding.bottomNavigationView.setOnItemSelectedListener {

            when(it.itemId){

                R.id.home -> {
                    //replaceFragment(Home())
                    // replaceFragment(Profile())




                    val fragment = Home()
                    val bundle = Bundle()
                    bundle.putString("myKey", message)
                    fragment.arguments = bundle

                    supportFragmentManager.beginTransaction().replace(R.id.frame_layout,fragment).commit()


                }
                R.id.profile ->{
                   // replaceFragment(Profile())




                    val fragment = Profile()
                    val bundle = Bundle()
                    bundle.putString("myKey", message)
                    fragment.arguments = bundle

                    supportFragmentManager.beginTransaction().replace(R.id.frame_layout,fragment).commit()

                }
                R.id.checklist -> {
                    //replaceFragment(Checklist())


                    val fragment = Checklist()
                    val bundle = Bundle()
                    bundle.putString("myKey", message)
                    fragment.arguments = bundle

                    supportFragmentManager.beginTransaction().replace(R.id.frame_layout,fragment).commit()





                }

                else ->{



                }

            }

            true

        }

        //val message = intent.getStringExtra("EXTRA_MESSAGE")
      //  if (message != null) {
       //     Log.d("IntentMessage", message)
      //  }

                 //  val fragment = Profile()
                 //   val bundle = Bundle()
                //  bundle.putString("myKey", message)
                //   fragment.arguments = bundle






    }



    private fun replaceFragment(homeFragment: Fragment) {
//        val message = intent.getStringExtra("EXTRA_MESSAGE")
//
//        val me = message.toString()
//
//        val fragment = Profile()
//        val bundle = Bundle()
//        bundle.putString("myKey", me)
//        fragment.arguments = bundle


        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,homeFragment)
        fragmentTransaction.commit()

    }





}
package com.yigitkula.pokemonlistapp.view

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.yigitkula.pokemonlistapp.R
import com.yigitkula.pokemonlistapp.network.NetworkConnection
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var analytics: FirebaseAnalytics
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        networkCheck()

    }
    private fun networkCheck(){
        val networkConnection = NetworkConnection(applicationContext)
        lifecycleScope.launch{
            networkConnection.observe(this@MainActivity, Observer{ isConnected->
                if(isConnected){
                    Toast.makeText(this@MainActivity,"Welcome",Toast.LENGTH_LONG).show()
                }

                else{
                    val mAlerDiaglog = AlertDialog.Builder(this@MainActivity)
                    mAlerDiaglog.setTitle("INTERNET NOT FOUND")
                    mAlerDiaglog.setMessage("Check your internet connection!")
                    mAlerDiaglog.setIcon(R.mipmap.ic_launcher_round)
                    mAlerDiaglog.setPositiveButton("Try again!"){dialogInterface, i-> networkCheck()}
                    mAlerDiaglog.setNegativeButton("Exit"){dialogInterface, i-> finish()}
                    mAlerDiaglog.show()
                }
            })
        }
    }

}

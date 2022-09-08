package com.yigitkula.pokemonlistapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.yigitkula.pokemonlistapp.R
import com.yigitkula.pokemonlistapp.network.NetworkConnection
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        networkCheck()
       supportActionBar?.hide()
    }
    private fun networkCheck(){
        val networkConnection = NetworkConnection(applicationContext)
        lifecycleScope.launch{
            networkConnection.observe(this@MainActivity, Observer{ isConnected->
                if(isConnected){
                   Toast.makeText(this@MainActivity,"Welcome To Pokemon List",Toast.LENGTH_LONG).show()
                }

                else{
                    val mAlerDiaglog = MaterialAlertDialogBuilder(this@MainActivity)
                    mAlerDiaglog.setTitle("INTERNET NOT FOUND")
                    mAlerDiaglog.setMessage("Check your internet connection!")
                    mAlerDiaglog.setIcon(R.mipmap.pokedex)
                    mAlerDiaglog.setPositiveButton("Try again!"){dialog, i-> networkCheck()}
                    mAlerDiaglog.setNegativeButton("Exit"){dialog, i-> finish()}
                    mAlerDiaglog.show()
                }
            })
        }
    }

}

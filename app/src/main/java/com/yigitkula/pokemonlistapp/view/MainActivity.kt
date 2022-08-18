package com.yigitkula.pokemonlistapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yigitkula.pokemonlistapp.R



class MainActivity : AppCompatActivity() {
   /*private val showDetail = object:BroadcastReceiver(){
        override fun onReceive(p0: Context?, intent: Intent?) {
            if(intent!!.action!!.toString() == Common.KEY_ENABLE_HOME)
            {
                supportActionBar!!.setDisplayHomeAsUpEnabled(true)
                supportActionBar!!.setDisplayShowHomeEnabled(true)
                val pokemonDetail:DetailFragment = DetailFragment.getInstance()
                val position = intent.getIntExtra("position",-1)
                val  bundle = Bundle()
                bundle.putInt("position",position)
                pokemonDetail.arguments = bundle

                val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.fragment_lis,pokemonDetail)
                fragmentTransaction.addToBackStack("detail")
                fragmentTransaction.commit()

                val pokemon :Pokemon = Common.pokemonList[position]
                //   Toast.makeText(context,"Click Pokemon" + pokemonList[position],Toast.LENGTH_SHORT).show()
                Toast.makeText(p0,pokemon.toString(),Toast.LENGTH_SHORT).show()
            }
        }

    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}
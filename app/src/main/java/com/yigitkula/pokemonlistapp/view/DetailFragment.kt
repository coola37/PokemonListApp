package com.yigitkula.pokemonlistapp.view

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.ContextCompat.startForegroundService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.yigitkula.pokemonlistapp.R
import com.yigitkula.pokemonlistapp.databinding.FragmentDetailBinding
import com.yigitkula.pokemonlistapp.util.downloadFromUrl
import com.yigitkula.pokemonlistapp.viewmodel.DetailViewModel
import com.yigitkula.pokemonlistapp.window.ForegroundService
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.bottomNavigationView
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.popup_window.*


class DetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel
    private var pokemonUuid = 0
    private lateinit var dataBinding : FragmentDetailBinding
    private lateinit var fragmentActivity: FragmentActivity


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            pokemonUuid = DetailFragmentArgs.fromBundle(it).id
        }

        viewModel=ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.getDataFromRoom(pokemonUuid)

        button.setOnClickListener {
            checkOverlayPermission()
            startPokemonDrawService()
        }
        button2.setOnClickListener {
            val action = DetailFragmentDirections.actionDetailFragmentToListFragment()
            Navigation.findNavController(it).navigate(action)
        }

        observeLiveData()

        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.exit -> {
                    activity?.finish()
                    true
                }
                R.id.home -> {
                    val action = DetailFragmentDirections.actionDetailFragmentToListFragment()
                    Navigation.findNavController(view).navigate(action)
                    true
                }
                else -> false
            }

            true
        }


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentActivity = context as FragmentActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_detail,container,false)
        return dataBinding.root
    }
    fun startPokemonDrawService() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Settings.canDrawOverlays(fragmentActivity)) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    startForegroundService(fragmentActivity, Intent(fragmentActivity, ForegroundService::class.java)
                        .putExtra("pokemonData", dataBinding.selectedPokemon))
                } else {
                    fragmentActivity.startService(Intent(fragmentActivity,ForegroundService::class.java)
                        .putExtra("pokemonData", dataBinding.selectedPokemon))
                }
            }
        } else {
            fragmentActivity.startService(Intent(fragmentActivity, ForegroundService::class.java)
                .putExtra("pokemonData", dataBinding.selectedPokemon))
        }
    }
    fun checkOverlayPermission() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(fragmentActivity)) {
                // send user to the device settings
                val myIntent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
                startActivity(myIntent)
            }
        }
    }
    private fun observeLiveData(){
        viewModel.pokemonLiveData.observe(viewLifecycleOwner, Observer { pokemon->
            pokemon?.let {
                dataBinding.selectedPokemon = it


              /*  pokeName.text = pokemon.pokemonName
                pokeHeight.text=pokemon.pokemonHeight
                pokeWeight.text=pokemon.pokemonWeight
                context?.let {
                    pokemonImage.downloadFromUrl(pokemon.pokemonImg, placeholderProgressBar(it))
                }*/
            }
        })
    }
    override fun onResume() {
        super.onResume()
       // startPokemonDrawService()
    }
}
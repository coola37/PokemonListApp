package com.yigitkula.pokemonlistapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.yigitkula.pokemonlistapp.R
import com.yigitkula.pokemonlistapp.databinding.FragmentDetailBinding
import com.yigitkula.pokemonlistapp.model.Pokemon
import com.yigitkula.pokemonlistapp.util.downloadFromUrl
import com.yigitkula.pokemonlistapp.util.placeholderProgressBar
import com.yigitkula.pokemonlistapp.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel
    private var pokemonUuid = 0
    private lateinit var dataBinding : FragmentDetailBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            pokemonUuid = DetailFragmentArgs.fromBundle(it).id
        }

        viewModel=ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.getDataFromRoom(pokemonUuid)


        observeLiveData()
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_detail,container,false)
        return dataBinding.root

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
}
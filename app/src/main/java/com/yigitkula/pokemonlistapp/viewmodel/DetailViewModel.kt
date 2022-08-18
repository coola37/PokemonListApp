package com.yigitkula.pokemonlistapp.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yigitkula.pokemonlistapp.model.Pokemon
import com.yigitkula.pokemonlistapp.retrofit.PokemonDatabase
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) :BaseViewModel(application){
    val pokemonLiveData = MutableLiveData<Pokemon>()

    fun getDataFromRoom(uuid: Int){
        launch {
            val dao = PokemonDatabase(getApplication()).pokemonDao()
            val pokemon = dao.getPokemon(uuid)
            pokemonLiveData.value = pokemon
        }

    }


}
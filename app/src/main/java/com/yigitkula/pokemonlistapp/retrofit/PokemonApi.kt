package com.yigitkula.pokemonlistapp.retrofit

import com.yigitkula.pokemonlistapp.model.Pokedex
import com.yigitkula.pokemonlistapp.model.Pokemon
import io.reactivex.Single
import retrofit2.http.GET

interface PokemonApi {
    //https://raw.githubusercontent.com/Biuni/PokemonGO-Pokedex/master/pokedex.json
    @GET("pokedex.json")
    fun getPokemons(): Single<Pokedex>
}
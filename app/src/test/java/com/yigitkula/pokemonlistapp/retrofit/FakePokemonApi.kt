package com.yigitkula.pokemonlistapp.retrofit

import com.yigitkula.pokemonlistapp.model.Pokedex
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface FakePokemonApi {
    //https://raw.githubusercontent.com/Biuni/PokemonGO-Pokedex/master/pokedex.json
    @GET("pokedex.json")
    fun fakeGetPokemons(): Call<Pokedex>
}
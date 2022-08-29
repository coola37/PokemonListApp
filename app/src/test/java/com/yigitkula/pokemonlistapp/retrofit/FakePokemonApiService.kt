package com.yigitkula.pokemonlistapp.retrofit

import com.yigitkula.pokemonlistapp.model.Pokedex

import io.reactivex.Single
import retrofit2.Call

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class FakePokemonApiService {
    val FAKEBASE_URL = "https://raw.githubusercontent.com/Biuni/PokemonGO-Pokedex/master/"
    val fakeApi = Retrofit.Builder()
        .baseUrl(FAKEBASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(FakePokemonApi::class.java)
    fun getData(): Call<Pokedex> {
        return fakeApi.fakeGetPokemons()
    }
}



package com.yigitkula.pokemonlistapp.Adapter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.yigitkula.pokemonlistapp.adapter.PokemonListAdapter
import com.yigitkula.pokemonlistapp.model.Pokemon
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.google.common.truth.Truth.assertThat

@ExperimentalCoroutinesApi
class PokemonListAdapterTest (val pokemonList : ArrayList<Pokemon>) {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var pokeAdap: PokemonListAdapter


    @Before
    fun setup(){

        pokeAdap = PokemonListAdapter(pokemonList)
    }

    @Test
    fun updatePokemonListTest(){
        val exPoke = Pokemon(1, "1","exPika","asd.com","5","5")

        pokemonList.add(exPoke)
        pokeAdap.updatePokemonList(pokemonList)
        val list2 = pokeAdap.pokemonList

        assertThat(list2).contains(exPoke)
    }
    @Test
    fun getItemCountTest(){
        assertThat(pokeAdap.itemCount).isNotNull()

    }

    @Test
    fun onClickTest(){

    }
}
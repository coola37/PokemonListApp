package com.yigitkula.pokemonlistapp.viewmodel

import android.app.Application
import android.app.ProgressDialog.show
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import com.yigitkula.pokemonlistapp.model.Pokemon
import com.yigitkula.pokemonlistapp.retrofit.PokemonDatabase
import com.yigitkula.pokemonlistapp.util.Status
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.bouncycastle.its.asn1.EndEntityType.app
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class ListViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    var pokemons = MutableLiveData<List<Pokemon>>()
    /*
   @get:Rule
   var mainCoroutineRule = MainCoroutineRule()
    */

    private val app = Mockito.mock(Application::class.java)
    private lateinit var viewModel : ListViewModel
    @Before
    fun setup() {
        viewModel = ListViewModel(app)


    }

    @Test
    fun showPokemonTest(){
        val exPokemon = Pokemon(1,"1","ExPikachı","example.com","5","5")
        val exPokemon2 = Pokemon(2,"1","ExPikachı","example.com","5","5")
        val exPokemon3 = Pokemon(1,"1","ExPikachı","example.com","5","5")
        val pokemonList: List<Pokemon> = arrayListOf(exPokemon,exPokemon3,exPokemon3)

        val show = viewModel.showPokemons(pokemonList)
        val pokemons= viewModel.pokemons
        assertThat(show).takeIf{ pokemons.value.isNullOrEmpty()}
    }


}
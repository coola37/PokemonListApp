package com.yigitkula.pokemonlistapp.viewmodel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import com.google.common.truth.Truth.assertThat
import com.yigitkula.pokemonlistapp.model.Pokemon
import com.yigitkula.pokemonlistapp.retrofit.PokemonDao
import com.yigitkula.pokemonlistapp.retrofit.PokemonDatabase
import kotlinx.coroutines.runBlocking


@ExperimentalCoroutinesApi
class DetailViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val app = Mockito.mock(Application::class.java)
    private lateinit var vm: DetailViewModel


    @Before
    fun setup(){
        vm = DetailViewModel(app)
    }

    @Test
    fun incomingDataTesting() = runBlocking{
       val list = vm.getDataFromRoom(1)
        assertThat(list).isNotNull()
    }

}
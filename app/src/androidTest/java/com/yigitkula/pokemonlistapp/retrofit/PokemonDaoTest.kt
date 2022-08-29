package com.yigitkula.pokemonlistapp.retrofit

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.yigitkula.pokemonlistapp.model.Pokemon
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import com.getOrAwaitValue
import com.google.common.truth.Truth.assertThat


import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@SmallTest
@ExperimentalCoroutinesApi
class PokemonDaoTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: PokemonDatabase
    private lateinit var dao: PokemonDao

    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),PokemonDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.pokemonDao()
    }

    @After
    fun teardown(){
        database.close()
    }

    @Test
    fun insertPokemonTesting() = runBlocking {
        val examplePokemon = Pokemon(1, "1", "yigit", "test.com", "5", "5")
        dao.insertAll(examplePokemon)
        val list = dao.getAllPokemons()
        assertThat(list).contains(examplePokemon) // contains -> içinde var mı ?
    }
    @Test
    fun deletePokemonTesting() = runBlocking {
        val examplePokemon = Pokemon(1, "1", "yigit", "test.com", "5", "5")
        dao.insertAll(examplePokemon)
        dao.deleteAllPokemons()
        val list = dao.getAllPokemons()
        assertThat(list).doesNotContain(examplePokemon)



    }
}












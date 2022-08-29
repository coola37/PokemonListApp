package com.yigitkula.pokemonlistapp.retrofit

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PokemonApiServiceTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @Test
    fun `api connection control` () {
       val service = FakePokemonApiService().fakeApi
       val response = service.fakeGetPokemons()

        assertThat(response.execute().code()).isEqualTo(200)



    }
}
package com.yigitkula.pokemonlistapp.retrofit

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.yigitkula.pokemonlistapp.model.Pokemon

@Dao
interface PokemonDao {

    @Insert
    suspend fun insertAll(vararg pokemons: Pokemon) : List<Long>
    //Insert -> Insert into
    //suspend -> coroutine, pause & resume
    //vararg -> multiple pokemon objects
    // List<Long> -> primary keys

    @Query("SELECT * FROM pokemon")
    suspend fun getAllPokemons() : List<Pokemon>

    @Query("SELECT * FROM pokemon WHERE uuid = :pokemonId")
    suspend fun getPokemon(pokemonId: Int) : Pokemon

    @Query("DELETE FROM pokemon")
    suspend fun deleteAllPokemons()

}
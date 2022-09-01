package com.yigitkula.pokemonlistapp.network

import android.content.Context
import android.net.ConnectivityManager
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class NetworkConnectionTest{
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var networkConnection: NetworkConnection

    @Before
    fun setup(){
        networkConnection = NetworkConnection(ApplicationProvider.getApplicationContext())
    }



    @Test
    fun `network check test`(){
        val request = networkConnection.lollipopNetworkRequest()
        assertThat(request).isEqualTo(true)
    }
}
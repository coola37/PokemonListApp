package com.yigitkula.pokemonlistapp.network

import android.net.ConnectivityManager
import android.net.Network
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import androidx.lifecycle.LiveData
import androidx.test.filters.SmallTest


@SmallTest
@ExperimentalCoroutinesApi
class NetworkConnectionTestt{
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var networkConnection: NetworkConnection
    private lateinit var nCallback: ConnectivityManager.NetworkCallback

    @Before
    fun setup(){
        networkConnection = NetworkConnection(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun checkLollipopRequest(){
        val request = networkConnection.lollipopNetworkRequest()
        assertThat(request).isNotNull()
    }

    @Test
    fun connectivityManagerCallbackTest(){
        val callback = networkConnection.connectivityManagerCallback()
        assertThat(callback).runCatching { IllegalAccessError("Error") }

    }

    @Test
    fun updateConnectionTest(){
        val update = networkConnection.updateConnection()
        assertThat(update).takeUnless { true }
    }
}
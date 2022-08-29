package com.yigitkula.pokemonlistapp.view

import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.Fragment
import com.google.common.truth.Truth
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import com.google.common.truth.Truth.assertThat

import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ListFragmentTest: Fragment() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var fragment : ListFragment

    @Before
    fun setup(){
        fragment = ListFragment()

    }

    @Test
    fun observeLiveDataTest(){
        val observe = fragment.observeLiveData()
    }

}

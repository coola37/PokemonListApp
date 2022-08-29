package com.yigitkula.pokemonlistapp.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.analytics.FirebaseAnalytics
import com.yigitkula.pokemonlistapp.R
import com.yigitkula.pokemonlistapp.adapter.PokemonListAdapter
import com.yigitkula.pokemonlistapp.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {
    private lateinit var viewModel: ListViewModel
    private val pokeAdapter = PokemonListAdapter(arrayListOf())



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refreshData()

        rvPokemon.layoutManager = LinearLayoutManager(context)
        rvPokemon.adapter = pokeAdapter

        rLayout.setOnRefreshListener {
            rvPokemon.visibility = View.GONE
            pokemonError.visibility = View.GONE
            pokemonProBarLoading.visibility = View.VISIBLE
            viewModel.refreshFromAPI()
            rLayout.isRefreshing = false
        }
        observeLiveData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val itemView = inflater.inflate(R.layout.fragment_list, container, false)
        return itemView
    }


     fun observeLiveData() {
            viewModel.pokemons.observe(viewLifecycleOwner, Observer { pokemons ->

                pokemons?.let {
                    rvPokemon.visibility = View.VISIBLE
                    pokeAdapter.updatePokemonList(pokemons)
                }
            })
//            viewModel.pokemonError.observe(viewLifecycleOwner, Observer { error ->
//                error?.let {
//                    if (it) {
//                        pokemonError.visibility = View.VISIBLE
//
//                    } else {
//                        pokemonError.visibility = View.GONE
//                    }
//                }
//            })
            viewModel.pokemonLoading.observe(viewLifecycleOwner, Observer { loading ->
                loading?.let {
                    if (it) {
                        pokemonProBarLoading.visibility = View.VISIBLE
                        rvPokemon.visibility = View.VISIBLE

                    } else {
                        pokemonProBarLoading.visibility = View.GONE
                    }
                }

            })
        }
}
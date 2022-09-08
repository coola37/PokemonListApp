package com.yigitkula.pokemonlistapp.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.yigitkula.pokemonlistapp.R
import com.yigitkula.pokemonlistapp.adapter.PokemonListAdapter
import com.yigitkula.pokemonlistapp.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {
    private val viewModel: ListViewModel by viewModels()
    private val pokeAdapter = PokemonListAdapter(arrayListOf())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.search(newText ?: "")
                return true
            }
        })

        viewModel.refreshData()

        rvPokemon.layoutManager = LinearLayoutManager(context)
        rvPokemon.adapter = pokeAdapter

        rLayout.setOnRefreshListener {
            rvPokemon.visibility = View.GONE

            pokemonProBarLoading.visibility = View.VISIBLE
            viewModel.refreshFromAPI()
            rLayout.isRefreshing = false
        }
        observeLiveData()

        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.exit -> {
                    activity?.finish()
                    true
                }
                R.id.home -> {
                    rvPokemon.visibility = View.GONE

                    pokemonProBarLoading.visibility = View.VISIBLE
                    viewModel.refreshFromAPI()
                    rLayout.isRefreshing = false
                    true
                }
                else -> false
            }

            true
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val itemView = inflater.inflate(R.layout.fragment_list, container, false)
        return itemView
    }


    fun observeLiveData() {
        viewModel.pokemons.observe(viewLifecycleOwner) { pokemons ->
            pokemons?.let {
                rvPokemon.visibility = View.VISIBLE
                pokeAdapter.updatePokemonList(pokemons)
            }
        }
        viewModel.pokemonLoading.observe(viewLifecycleOwner) { loading ->
            loading?.let {
                if (it) {
                    pokemonProBarLoading.visibility = View.VISIBLE
                    rvPokemon.visibility = View.VISIBLE

                } else {
                    pokemonProBarLoading.visibility = View.GONE
                }
            }
        }
    }
}
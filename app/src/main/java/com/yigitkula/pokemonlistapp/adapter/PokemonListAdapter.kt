package com.yigitkula.pokemonlistapp.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.yigitkula.pokemonlistapp.model.Pokemon
import com.yigitkula.pokemonlistapp.R
import com.yigitkula.pokemonlistapp.databinding.PokemonListItemBinding
import com.yigitkula.pokemonlistapp.util.downloadFromUrl
import com.yigitkula.pokemonlistapp.util.placeholderProgressBar

import com.yigitkula.pokemonlistapp.view.ListFragmentDirections
import com.yigitkula.pokemonlistapp.view.MainActivity
import kotlinx.android.synthetic.main.fragment_detail.view.*
import kotlinx.android.synthetic.main.pokemon_list_item.view.*
import java.util.*


class PokemonListAdapter(val pokemonList: ArrayList<Pokemon>) :RecyclerView.Adapter<PokemonListAdapter.MyViewHolder>(), ItemClickListener {


    class MyViewHolder(var view: PokemonListItemBinding) : RecyclerView.ViewHolder(view.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val inflater = LayoutInflater.from(parent.context)
      //  val view = inflater.inflate(R.layout.pokemon_list_item,parent,false)
        val view = DataBindingUtil.inflate<PokemonListItemBinding>(inflater,R.layout.pokemon_list_item,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.view.pokemon = pokemonList[position]
        holder.view.listener = this

    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    fun updatePokemonList(newPokemonList: List<Pokemon>){
        pokemonList.clear()
        pokemonList.addAll(newPokemonList)
        notifyDataSetChanged()
    }

    override fun onPokemonClick(v: View) {
        val uuid = v.pokemonUuidText.text.toString().toInt()
        val action = ListFragmentDirections.actionListFragmentToDetailFragment(uuid)
        Navigation.findNavController(v).navigate(action)


    }
}

// 13,21


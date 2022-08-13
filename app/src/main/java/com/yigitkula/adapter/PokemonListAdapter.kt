package com.yigitkula.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yigitkula.model.Pokemon
import com.yigitkula.pokemonlistapp.R

class PokemonListAdapter(
    internal var context: Context,
    internal var pokemonList:List<Pokemon>) :RecyclerView.Adapter<PokemonListAdapter.MyViewHolder>() {
        inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

            internal var txt_pokemonName :TextView
            init {
                txt_pokemonName = itemView.findViewById(R.id.pokeLName) as TextView
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.pokemon_list_item,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.txt_pokemonName.text = pokemonList[position].name
        }

    override fun getItemCount(): Int {
        return pokemonList.size
    }
}
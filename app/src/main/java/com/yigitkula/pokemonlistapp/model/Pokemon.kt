package com.yigitkula.pokemonlistapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Pokemon (

    @ColumnInfo(name="id")
    @SerializedName("id")
    val pokemonId: Int = 0,
    @ColumnInfo(name="num")
    @SerializedName("num")
    val pokemonNum: String?=null,
    @ColumnInfo(name="name")
    @SerializedName("name")
    val pokemonName: String?=null,
    @ColumnInfo(name="img")
    @SerializedName("img")
    val pokemonImg: String?=null,
    @ColumnInfo(name="height")
    @SerializedName("height")
    val pokemonHeight: String?=null,
    @ColumnInfo(name="weight")
    @SerializedName("weight")
    val pokemonWeight: String?=null){

    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
    }




    /*


    var id: Int = 0
    var num: String?=null
    var name: String?=null
    var img: String?=null
    var height: String?=null
    var weight: String?=null


    */

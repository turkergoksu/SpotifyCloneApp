package me.turkergoksu.spotifycloneapp.data.remote

/**
 * Created by turkergoksu on 17-Nov-19.
 */

data class Stream (
    val id : Int,
    val is_external : Boolean,
    val isfile : Boolean,
    val rate : Int,
    val works : Boolean
)
package me.turkergoksu.spotifycloneapp

import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by turkergoksu on 17-Nov-19.
 */

interface SpotifyApiService {

    @GET("popularRadios.json")
    fun getPopularRadios(): Call<List<Radio>>

    @GET("locationRadios.json")
    fun getLocationRadios(): Call<List<Radio>>


}
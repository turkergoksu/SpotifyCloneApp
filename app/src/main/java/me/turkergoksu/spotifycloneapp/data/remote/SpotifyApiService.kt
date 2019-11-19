package me.turkergoksu.spotifycloneapp.data.remote

import io.reactivex.Single
import retrofit2.http.GET

/**
 * Created by turkergoksu on 17-Nov-19.
 */

interface SpotifyApiService {

    @GET("popularRadios.json")
    fun getPopularRadios(): Single<List<Radio>>

    @GET("locationRadios.json")
    fun getLocationRadios(): Single<List<Radio>>


}
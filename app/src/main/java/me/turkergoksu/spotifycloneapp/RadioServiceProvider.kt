package me.turkergoksu.spotifycloneapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by turkergoksu on 17-Nov-19.
 */
class RadioServiceProvider {

    private val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BuildConfig.SERVER_URL).build()

    private val apiService = retrofit.create(SpotifyApiService::class.java)

    fun getRadioService(): SpotifyApiService = apiService
}
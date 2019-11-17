package me.turkergoksu.spotifycloneapp.radios

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import me.turkergoksu.spotifycloneapp.Radio
import me.turkergoksu.spotifycloneapp.RadioServiceProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by turkergoksu on 17-Nov-19.
 */

class RadiosFragment : Fragment() {

    private val radioServiceProvider = RadioServiceProvider()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Popular radios
        radioServiceProvider.getRadioService().getPopularRadios()
            .enqueue(object : Callback<List<Radio>> {
                override fun onFailure(call: Call<List<Radio>>, t: Throwable) {
                    Log.v("TEST", "${t.message}")
                }

                override fun onResponse(call: Call<List<Radio>>, response: Response<List<Radio>>) {
                    Log.v("TEST", "Popular Radios: ${response.body().toString()}")
                }
            })

        // Location based radios
        radioServiceProvider.getRadioService().getLocationRadios()
            .enqueue(object : Callback<List<Radio>> {
                override fun onFailure(call: Call<List<Radio>>, t: Throwable) {
                    Log.v("TEST", "${t.message}")
                }

                override fun onResponse(call: Call<List<Radio>>, response: Response<List<Radio>>) {
                    Log.v("TEST", "Location-Based Radios: ${response.body().toString()}")
                }
            })

    }

    companion object {
        //        fun newInstance():Fragment{
//            return FavoritesFragment()
//        }
        fun newInstance() = RadiosFragment()
    }
}
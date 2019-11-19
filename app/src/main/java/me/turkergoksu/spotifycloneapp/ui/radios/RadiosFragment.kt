package me.turkergoksu.spotifycloneapp.ui.radios

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.turkergoksu.spotifycloneapp.data.RadioDataSource
import me.turkergoksu.spotifycloneapp.data.remote.RadioServiceProvider

/**
 * Created by turkergoksu on 17-Nov-19.
 */

class RadiosFragment : Fragment() {

    val radioDataSource = RadioDataSource()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    @SuppressLint("CheckResult")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        fetchRadioPage()
    }

    @SuppressLint("CheckResult")
    private fun fetchRadioPage() {
        val popularObservable = radioDataSource.fetchPopularRadios()
        val locationObservable = radioDataSource.fetchLocationRadios()

        Observable.combineLatest(popularObservable, locationObservable, RadioPageCombiner())
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe {
                Log.v(
                    "TEST",
                    "Popular Status -> ${it.popularRadios.status} : Location Status -> ${it.locationRadios.status}"
                )
            }
    }

    companion object {
        //        fun newInstance():Fragment{
//            return FavoritesFragment()
//        }
        fun newInstance() = RadiosFragment()
    }
}
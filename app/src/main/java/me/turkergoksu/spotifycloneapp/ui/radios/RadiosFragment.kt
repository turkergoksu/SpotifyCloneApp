package me.turkergoksu.spotifycloneapp.ui.radios

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.turkergoksu.spotifycloneapp.R
import me.turkergoksu.spotifycloneapp.data.RadioDataSource
import me.turkergoksu.spotifycloneapp.data.Status
import me.turkergoksu.spotifycloneapp.data.Status.*
import me.turkergoksu.spotifycloneapp.data.remote.RadioServiceProvider
import me.turkergoksu.spotifycloneapp.databinding.FragmentRadiosBinding

/**
 * Created by turkergoksu on 17-Nov-19.
 */

class RadiosFragment : Fragment() {

    private lateinit var binding: FragmentRadiosBinding

    private val popularRadioAdapter = RadiosAdapter()
    private val locationRadioAdapter = RadiosAdapter()

    val radioDataSource = RadioDataSource()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_radios, container, false)
        binding.recyclerViewPopularStations.adapter = popularRadioAdapter
        binding.recyclerViewLocationStations.adapter = locationRadioAdapter
        return binding.root
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
                renderUI(it)
            }
    }

    private fun renderUI(radiosFragmentViewState: RadiosFragmentViewState) {
        when (radiosFragmentViewState.popularRadios.status) {
            SUCCESS -> {
                binding.progressPopularRadios.visibility = View.GONE
                // Forcing with !! because if status = SUCCESS then data won't be null
                // But I did the opposite in location based radios
                popularRadioAdapter.setRadioList(radiosFragmentViewState.popularRadios.data!!)
            }
            LOADING -> {
                binding.progressPopularRadios.visibility = View.VISIBLE
            }
        }

        when (radiosFragmentViewState.locationRadios.status) {
            SUCCESS -> {
                binding.progressLocationRadios.visibility = View.GONE
                // Even though it's not going to be null, decided to return a empty list if null
                locationRadioAdapter.setRadioList(radiosFragmentViewState.locationRadios.data ?: arrayListOf())
            }
            LOADING -> {
                binding.progressLocationRadios.visibility = View.VISIBLE
            }
        }
    }

    companion object {
        //        fun newInstance():Fragment{
//            return FavoritesFragment()
//        }
        fun newInstance() = RadiosFragment()
    }
}
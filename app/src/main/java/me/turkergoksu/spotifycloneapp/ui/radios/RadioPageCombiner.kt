package me.turkergoksu.spotifycloneapp.ui.radios

import io.reactivex.functions.BiFunction
import me.turkergoksu.spotifycloneapp.data.Resource
import me.turkergoksu.spotifycloneapp.data.remote.Radio

/**
 * Created by turkergoksu on 19-Nov-19.
 */

class RadioPageCombiner : BiFunction<Resource<List<Radio>>, Resource<List<Radio>>, RadiosFragmentViewState>{

    override fun apply(
        popularRadios: Resource<List<Radio>>,
        locationRadios: Resource<List<Radio>>
    ): RadiosFragmentViewState {
        return RadiosFragmentViewState(popularRadios, locationRadios)
    }
}
package me.turkergoksu.spotifycloneapp.ui.radios

import me.turkergoksu.spotifycloneapp.data.Resource
import me.turkergoksu.spotifycloneapp.data.remote.Radio

/**
 * Created by turkergoksu on 19-Nov-19.
 */

data class RadiosFragmentViewState(
    val popularRadios: Resource<List<Radio>>,
    val locationRadios: Resource<List<Radio>>
)
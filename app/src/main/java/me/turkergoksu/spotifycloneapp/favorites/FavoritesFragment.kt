package me.turkergoksu.spotifycloneapp.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import me.turkergoksu.spotifycloneapp.R

/**
 * Created by turkergoksu on 17-Nov-19.
 */

class FavoritesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    /**
     * In Kotlin, if you want to write a function or any member of the class that can be called
     * without having the instance of the class then you can write the same as a member of a
     * companion object inside the class. So, by declaring the companion object, you can access
     * the members of the class by class name only.
     */

    companion object {
        //        fun newInstance():Fragment{
//            return FavoritesFragment()
//        }
        fun newInstance() = FavoritesFragment()

    }

}
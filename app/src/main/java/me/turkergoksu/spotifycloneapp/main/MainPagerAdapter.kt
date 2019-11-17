package me.turkergoksu.spotifycloneapp.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import me.turkergoksu.spotifycloneapp.R
import me.turkergoksu.spotifycloneapp.favorites.FavoritesFragment
import me.turkergoksu.spotifycloneapp.radios.RadiosFragment
import java.lang.IllegalStateException

/**
 * Created by turkergoksu on 17-Nov-19.
 */

class MainPagerAdapter(
    context: Context,
    fragmentManager: FragmentManager,
    behavior: Int
) :
    FragmentStatePagerAdapter(fragmentManager, behavior) {

    private val tabs = context.applicationContext.resources.getStringArray(R.array.tabs)

    override fun getItem(position: Int): Fragment {
        return when (position) {
            TAB_INDEX_RADIOS -> RadiosFragment.newInstance()
            TAB_INDEX_FAVORITES -> FavoritesFragment.newInstance()
            else -> throw IllegalStateException("Cannot find fragment. position: $position")
        }
    }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence? {
        return tabs[position]
    }

    companion object {
        private const val TAB_INDEX_RADIOS = 0
        private const val TAB_INDEX_FAVORITES = 1

    }
}
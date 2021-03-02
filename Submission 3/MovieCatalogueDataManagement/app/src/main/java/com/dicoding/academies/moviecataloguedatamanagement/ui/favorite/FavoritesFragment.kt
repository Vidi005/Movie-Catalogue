package com.dicoding.academies.moviecataloguedatamanagement.ui.favorite

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import com.dicoding.academies.moviecataloguedatamanagement.R
import com.dicoding.academies.moviecataloguedatamanagement.databinding.FragmentFavoritesBinding
import com.dicoding.academies.moviecataloguedatamanagement.ui.favorite.adapter.SectionsPagerAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView

class FavoritesFragment : Fragment() {

    private lateinit var fragmentFavoritesBinding: FragmentFavoritesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentFavoritesBinding =
            FragmentFavoritesBinding.inflate(layoutInflater, container, false)
        return fragmentFavoritesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setActionBar()
        setPagerAdapter()
        val navView: BottomNavigationView = requireActivity().findViewById(R.id.nav_view)
        navView.visibility = View.VISIBLE
    }

    private fun setActionBar() {
        val drawable = ResourcesCompat.getDrawable(resources, R.drawable.ic_tmdb, null)
        val bitmap = drawable?.toBitmap()
        val scaledDrawable = BitmapDrawable(resources,
            bitmap?.let { Bitmap.createScaledBitmap(it, 60, 60, true) })
        (activity as? AppCompatActivity?)?.supportActionBar?.apply {
            setHomeAsUpIndicator(scaledDrawable)
            setHomeButtonEnabled(true)
            setDisplayHomeAsUpEnabled(true)
            title = getString(R.string.title_favorites)
        }
    }

    private fun setPagerAdapter() {
        val sectionsPagerAdapter = SectionsPagerAdapter(requireContext(), childFragmentManager)
        fragmentFavoritesBinding.apply {
            viewPager.adapter = sectionsPagerAdapter
            tabLayout.setupWithViewPager(fragmentFavoritesBinding.viewPager)
        }
    }
}
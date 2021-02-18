package com.dicoding.academies.moviecataloguetmdb.ui

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import com.dicoding.academies.moviecataloguetmdb.R
import com.dicoding.academies.moviecataloguetmdb.databinding.FragmentHomeBinding
import com.dicoding.academies.moviecataloguetmdb.ui.adapter.SectionsPagerAdapter

class HomeFragment : Fragment() {

    private lateinit var fragmentHomeBinding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
          savedInstanceState: Bundle?): View {
        fragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return fragmentHomeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setActionBar()
        val sectionsPagerAdapter = SectionsPagerAdapter(requireContext(), childFragmentManager)
        fragmentHomeBinding.apply {
            viewPager.adapter = sectionsPagerAdapter
            tabLayout.setupWithViewPager(fragmentHomeBinding.viewPager)
        }
    }

    private fun setActionBar() {
        val drawable = ResourcesCompat.getDrawable(resources,R.drawable.ic_tmdb, null)
        val bitmap = drawable?.toBitmap()
        val scaledDrawable = BitmapDrawable(resources,
            bitmap?.let { Bitmap.createScaledBitmap(it, 60, 60, true) })
        (activity as AppCompatActivity?)?.supportActionBar?.apply {
            setHomeAsUpIndicator(scaledDrawable)
            setHomeButtonEnabled(true)
            setDisplayHomeAsUpEnabled(true)
            title = getString(R.string.app_title)
        }
    }
}
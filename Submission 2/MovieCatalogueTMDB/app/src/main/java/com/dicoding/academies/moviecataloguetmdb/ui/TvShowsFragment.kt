package com.dicoding.academies.moviecataloguetmdb.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.academies.moviecataloguetmdb.R
import com.dicoding.academies.moviecataloguetmdb.data.source.local.entity.TVShowEntity
import com.dicoding.academies.moviecataloguetmdb.databinding.FragmentTvShowsBinding
import com.dicoding.academies.moviecataloguetmdb.ui.DetailTvShowFragment.Companion.EXTRA_TV_SHOW_ID
import com.dicoding.academies.moviecataloguetmdb.ui.adapter.TVShowsAdapter
import com.dicoding.academies.moviecataloguetmdb.ui.callback.OnTVShowItemClickCallback
import com.dicoding.academies.moviecataloguetmdb.viewmodel.TVShowsViewModel
import com.dicoding.academies.moviecataloguetmdb.viewmodel.ViewModelFactory

class TvShowsFragment : Fragment(), OnTVShowItemClickCallback {

    private lateinit var fragmentTvShowsBinding: FragmentTvShowsBinding
    private lateinit var tvShowsAdapter: TVShowsAdapter
    private var tvShows = ArrayList<TVShowEntity>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentTvShowsBinding =
            FragmentTvShowsBinding.inflate(layoutInflater, container, false)
        return fragmentTvShowsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showMoviesList()
        getViewModelLiveData()
    }

    override fun onItemClicked(data: TVShowEntity) {
        val mBundle = Bundle().apply { data.id?.let { putInt(EXTRA_TV_SHOW_ID, it) } }
        NavHostFragment
            .findNavController(this)
            .navigate(R.id.action_homeFragment_to_detailTvShowFragment, mBundle)
    }

    override fun onItemShared(data: TVShowEntity) {
        val shareTvShow = "${getString(R.string.app_title)}:\n" +
            "${getString(R.string.link)}: ${getString(R.string.tv_show_base_url)}${data.id}\n" +
            "${getString(R.string.movie_title)}: ${data.name}\n" +
            "${getString(R.string.overview)}: ${data.overview}\n"
            "${getString(R.string.year)}: ${data.firstAirDate}"
        val mimeType = "text/plain"
        ShareCompat.IntentBuilder
            .from(requireActivity())
            .setType(mimeType)
            .setChooserTitle(getString(R.string.share_movie_title))
            .setText(shareTvShow)
            .startChooser()
    }

    private fun getViewModelLiveData() {
        if (parentFragment != null) {
            val factory = ViewModelFactory.getInstance()
            val viewModel = ViewModelProvider(this, factory)[TVShowsViewModel::class.java]
            showLoading(true)
            viewModel.getTvShows().observe(viewLifecycleOwner, {
                if (it != null) {
                    showTvShowItems(it)
                    showLoading(false)
                }
            })
        }
    }

    private fun showTvShowItems(tvShowItems: List<TVShowEntity>?) {
        tvShowsAdapter.setTVShows(tvShowItems)
        when {
            tvShowItems?.size as Int == 0 ->
                fragmentTvShowsBinding.groupEmptyItem.visibility = View.VISIBLE
            else -> fragmentTvShowsBinding.groupEmptyItem.visibility = View.GONE
        }
    }

    private fun showMoviesList() {
        tvShowsAdapter = TVShowsAdapter(tvShows, this)
        tvShowsAdapter.notifyDataSetChanged()
        with(fragmentTvShowsBinding.rvTvShow) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = tvShowsAdapter
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) fragmentTvShowsBinding.pbRvTvShow.visibility = View.VISIBLE
        else fragmentTvShowsBinding.pbRvTvShow.visibility = View.GONE
    }
}
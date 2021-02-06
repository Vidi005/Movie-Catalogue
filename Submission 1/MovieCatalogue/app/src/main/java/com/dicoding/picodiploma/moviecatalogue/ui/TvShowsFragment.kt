package com.dicoding.picodiploma.moviecatalogue.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.moviecatalogue.R
import com.dicoding.picodiploma.moviecatalogue.data.TVShowEntity
import com.dicoding.picodiploma.moviecatalogue.databinding.FragmentTvShowsBinding
import com.dicoding.picodiploma.moviecatalogue.ui.DetailTvShowFragment.Companion.EXTRA_TV_SHOW_NAME
import com.dicoding.picodiploma.moviecatalogue.ui.callback.OnTVShowItemClickCallback
import com.dicoding.picodiploma.moviecatalogue.ui.adapter.TvShowsAdapter
import com.dicoding.picodiploma.moviecatalogue.viewmodel.TvShowsViewModel

class TvShowsFragment : Fragment() {

    private lateinit var fragmentTvShowsBinding: FragmentTvShowsBinding
    private var tvShows = ArrayList<TVShowEntity>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentTvShowsBinding = FragmentTvShowsBinding.inflate(layoutInflater, container, false)
        return fragmentTvShowsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getViewModelData()
        showMoviesList()
    }

    private fun getViewModelData() {
        if (parentFragment != null) {
            val viewModel = ViewModelProvider(this,
                ViewModelProvider.NewInstanceFactory())[TvShowsViewModel::class.java]
            tvShows = viewModel.getTVShows()
        }
    }

    private fun showMoviesList() {
        val tvShowsAdapter = TvShowsAdapter()
        tvShowsAdapter.apply {
            notifyDataSetChanged()
            setTVShows(tvShows)
            setOnItemClickCallback(object : OnTVShowItemClickCallback {
                override fun onItemClicked(data: TVShowEntity) = setSelectedTvShow(data)
                override fun onItemShared(data: TVShowEntity) = shareSelectedMovie(data)
            })
        }
        with(fragmentTvShowsBinding.rvTvShow) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = tvShowsAdapter
        }
    }

    private fun setSelectedTvShow(data: TVShowEntity) {
        val mBundle = Bundle().apply { putString(EXTRA_TV_SHOW_NAME, data.tvShowName) }
        NavHostFragment
            .findNavController(this)
            .navigate(R.id.action_homeFragment_to_detailTvShowFragment, mBundle)
    }

    private fun shareSelectedMovie(data: TVShowEntity) {
        val shareTvShow = "${getString(R.string.app_title)}:\n" +
            "${getString(R.string.movie_title)}: ${data.tvShowName}\n" +
            "${getString(R.string.year)}: ${data.year}\n" +
            "${getString(R.string.genre)}: ${data.genre}\n" +
            "${getString(R.string.duration)}: ${data.duration}\n" +
            "${getString(R.string.user_score)}: ${data.score}\n" +
            "${getString(R.string.tag_line)}: ${data.tagLine}\n" +
            "${getString(R.string.overview)}: ${data.overview}\n" +
            "${getString(R.string.persons)}: ${data.person}"
        val mimeType = "text/plain"
        ShareCompat.IntentBuilder
            .from(requireActivity())
            .setType(mimeType)
            .setChooserTitle(getString(R.string.share_movie_title))
            .setText(shareTvShow)
            .startChooser()
    }
}
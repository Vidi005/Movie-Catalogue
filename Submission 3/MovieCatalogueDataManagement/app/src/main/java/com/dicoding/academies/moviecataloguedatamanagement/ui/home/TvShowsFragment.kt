package com.dicoding.academies.moviecataloguedatamanagement.ui.home

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.academies.moviecataloguedatamanagement.R
import com.dicoding.academies.moviecataloguedatamanagement.data.source.local.entity.TVShowEntity
import com.dicoding.academies.moviecataloguedatamanagement.databinding.FragmentTvShowsBinding
import com.dicoding.academies.moviecataloguedatamanagement.ui.DetailTvShowFragment.Companion.EXTRA_TV_SHOW_ID
import com.dicoding.academies.moviecataloguedatamanagement.ui.home.adapter.TVShowsAdapter
import com.dicoding.academies.moviecataloguedatamanagement.ui.home.callback.OnTVShowItemClickCallback
import com.dicoding.academies.moviecataloguedatamanagement.viewmodel.TVShowsViewModel
import com.dicoding.academies.moviecataloguedatamanagement.viewmodel.ViewModelFactory
import com.dicoding.academies.moviecataloguedatamanagement.vo.Status

class TvShowsFragment : Fragment(), OnTVShowItemClickCallback {

    private lateinit var fragmentTvShowsBinding: FragmentTvShowsBinding
    private lateinit var tvShowsAdapter: TVShowsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentTvShowsBinding = FragmentTvShowsBinding.inflate(layoutInflater, container, false)
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
            .navigate(R.id.action_navigation_home_to_detailTvShowFragment, mBundle)
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
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TVShowsViewModel::class.java]
            showLoading(true)
            viewModel.getTvShows().observe(viewLifecycleOwner, {
                if (it != null) {
                    when (it.status) {
                        Status.LOADING -> showLoading(true)
                        Status.SUCCESS -> {
                            showTvShowItems(it.data)
                            showLoading(false)
                        }
                        Status.ERROR -> {
                            showLoading(false)
                            showErrorAlert(it.message.toString())
                        }
                    }
                }
            })
        }
    }

    private fun showTvShowItems(tvShowItems: PagedList<TVShowEntity>?) {
        tvShowsAdapter.submitList(tvShowItems)
        when {
            tvShowItems?.size as Int == 0 ->
                fragmentTvShowsBinding.groupEmptyItem.visibility = View.VISIBLE
            else -> fragmentTvShowsBinding.groupEmptyItem.visibility = View.GONE
        }
    }

    private fun showMoviesList() {
        tvShowsAdapter = TVShowsAdapter(this)
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

    private fun showErrorAlert(dialogMsg: String) {
        val dialogTitle = getString(R.string.loading_failed_alert)
        val alertDialogBuilder = AlertDialog.Builder(activity as Activity)
        alertDialogBuilder
            .setTitle(dialogTitle)
            .setMessage(dialogMsg)
            .setPositiveButton(getString(R.string.ok_positive_btn)) { dialog, _ -> dialog.cancel() }
        val errorAlert = alertDialogBuilder.create()
        if (errorAlert.isShowing) errorAlert.cancel()
        else errorAlert.show()
    }
}
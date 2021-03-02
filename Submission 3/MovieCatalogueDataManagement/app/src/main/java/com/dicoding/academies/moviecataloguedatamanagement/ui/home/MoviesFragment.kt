package com.dicoding.academies.moviecataloguedatamanagement.ui.home

import android.content.Context
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
import com.dicoding.academies.moviecataloguedatamanagement.data.source.local.entity.MovieEntity
import com.dicoding.academies.moviecataloguedatamanagement.databinding.FragmentMoviesBinding
import com.dicoding.academies.moviecataloguedatamanagement.ui.DetailMovieFragment.Companion.EXTRA_MOVIE_ID
import com.dicoding.academies.moviecataloguedatamanagement.ui.home.adapter.MoviesAdapter
import com.dicoding.academies.moviecataloguedatamanagement.ui.home.callback.OnMovieItemClickCallback
import com.dicoding.academies.moviecataloguedatamanagement.viewmodel.MoviesViewModel
import com.dicoding.academies.moviecataloguedatamanagement.viewmodel.ViewModelFactory
import com.dicoding.academies.moviecataloguedatamanagement.vo.Status

class MoviesFragment : Fragment(), OnMovieItemClickCallback {

    private lateinit var fragmentMoviesBinding: FragmentMoviesBinding
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentMoviesBinding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return fragmentMoviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showMoviesList()
        getViewModelLiveData()
    }

    override fun onItemClicked(data: MovieEntity) {
        val mBundle = Bundle().apply { data.id?.let { putInt(EXTRA_MOVIE_ID, it) } }
        NavHostFragment
            .findNavController(this)
            .navigate(R.id.action_navigation_home_to_detailMovieFragment, mBundle)
    }

    override fun onItemShared(data: MovieEntity) {
        val shareMovie = "${getString(R.string.app_title)}:\n" +
            "${getString(R.string.link)}: ${getString(R.string.movie_base_url)}${data.id}\n" +
            "${getString(R.string.movie_title)}: ${data.title}\n" +
            "${getString(R.string.overview)}: ${data.overview}\n" +
            "${getString(R.string.release_date)}: ${data.releaseDate}"
        val mimeType = "text/plain"
        ShareCompat.IntentBuilder
            .from(requireActivity())
            .setType(mimeType)
            .setChooserTitle(getString(R.string.share_movie_title))
            .setText(shareMovie)
            .startChooser()
    }

    private fun getViewModelLiveData() {
        if (parentFragment != null) {
            val factory = ViewModelFactory.getInstance(requireContext())
            val viewModel = ViewModelProvider(this, factory)[MoviesViewModel::class.java]
            viewModel.getMovies().observe(viewLifecycleOwner, {
                if (it != null) {
                    when (it.status) {
                        Status.LOADING -> showLoading(true)
                        Status.SUCCESS -> {
                            showMovieItems(it.data)
                            showLoading(false)
                        }
                        Status.ERROR -> {
                            showLoading(false)
                            showErrorAlert("${it.message}")
                        }
                    }
                }
            })
        }
    }

    private fun showMovieItems(movieItems: PagedList<MovieEntity>?) {
        moviesAdapter.submitList(movieItems)
        when {
            movieItems?.size as Int == 0 ->
                fragmentMoviesBinding.groupEmptyItem.visibility = View.VISIBLE
            else -> fragmentMoviesBinding.groupEmptyItem.visibility = View.GONE
        }
    }

    private fun showMoviesList() {
        moviesAdapter = MoviesAdapter(this)
        with(fragmentMoviesBinding.rvMovie) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = moviesAdapter
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) fragmentMoviesBinding.pbRvMovie.visibility = View.VISIBLE
        else fragmentMoviesBinding.pbRvMovie.visibility = View.GONE
    }

    private fun showErrorAlert(dialogMsg: String) {
        val dialogTitle = getString(R.string.loading_failed_alert)
        val alertDialogBuilder = AlertDialog.Builder(context as Context)
        alertDialogBuilder
            .setTitle(dialogTitle)
            .setMessage(dialogMsg)
            .setPositiveButton(getString(R.string.ok_positive_btn)) { dialog, _ -> dialog.cancel() }
        val errorAlert = alertDialogBuilder.create()
        if (errorAlert.isShowing) errorAlert.cancel()
        else errorAlert.show()
    }
}
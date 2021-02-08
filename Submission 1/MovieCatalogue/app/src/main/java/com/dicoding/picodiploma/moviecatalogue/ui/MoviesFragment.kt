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
import com.dicoding.picodiploma.moviecatalogue.data.MovieEntity
import com.dicoding.picodiploma.moviecatalogue.databinding.FragmentMoviesBinding
import com.dicoding.picodiploma.moviecatalogue.ui.DetailMovieFragment.Companion.EXTRA_MOVIE_NAME
import com.dicoding.picodiploma.moviecatalogue.ui.adapter.MoviesAdapter
import com.dicoding.picodiploma.moviecatalogue.ui.callback.OnMovieItemClickCallback
import com.dicoding.picodiploma.moviecatalogue.viewmodel.MoviesViewModel

class MoviesFragment : Fragment() {

    private lateinit var fragmentMoviesBinding: FragmentMoviesBinding
    private var movies = ArrayList<MovieEntity>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentMoviesBinding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return fragmentMoviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getViewModelData()
        showMoviesList()
    }

    private fun getViewModelData() {
        if (parentFragment != null) {
            val viewModel = ViewModelProvider(this,
                ViewModelProvider.NewInstanceFactory())[MoviesViewModel::class.java]
            movies = viewModel.getMovies()
        }
    }

    private fun showMoviesList() {
        val moviesAdapter = MoviesAdapter()
        moviesAdapter.apply {
            notifyDataSetChanged()
            setMovies(movies)
            setOnItemClickCallback(object : OnMovieItemClickCallback {
                override fun onItemClicked(data: MovieEntity) = setSelectedMovie(data)
                override fun onItemShared(data: MovieEntity) = shareSelectedMovie(data)
            })
        }
        with(fragmentMoviesBinding.rvMovie) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = moviesAdapter
        }
    }

    private fun setSelectedMovie(data: MovieEntity) {
        val mBundle = Bundle().apply { putString(EXTRA_MOVIE_NAME, data.movieName) }
        NavHostFragment
            .findNavController(this)
            .navigate(R.id.action_homeFragment_to_detailMovieFragment, mBundle)
    }

    private fun shareSelectedMovie(data: MovieEntity) {
        val shareMovie = "${getString(R.string.app_title)}:\n" +
            "${getString(R.string.movie_title)}: ${data.movieName}\n" +
            "${getString(R.string.year)}: ${data.year}\n" +
            "${getString(R.string.release_date)}: ${data.release}\n" +
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
            .setText(shareMovie)
            .startChooser()
    }
}

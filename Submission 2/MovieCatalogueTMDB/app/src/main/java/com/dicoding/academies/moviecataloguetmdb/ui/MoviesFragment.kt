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
import com.dicoding.academies.moviecataloguetmdb.data.source.local.entity.MovieEntity
import com.dicoding.academies.moviecataloguetmdb.databinding.FragmentMoviesBinding
import com.dicoding.academies.moviecataloguetmdb.ui.DetailMovieFragment.Companion.EXTRA_MOVIE_ID
import com.dicoding.academies.moviecataloguetmdb.ui.adapter.MoviesAdapter
import com.dicoding.academies.moviecataloguetmdb.ui.callback.OnMovieItemClickCallback
import com.dicoding.academies.moviecataloguetmdb.viewmodel.MoviesViewModel
import com.dicoding.academies.moviecataloguetmdb.viewmodel.ViewModelFactory

class MoviesFragment : Fragment(), OnMovieItemClickCallback {

    private lateinit var fragmentMoviesBinding: FragmentMoviesBinding
    private lateinit var moviesAdapter: MoviesAdapter
    private var movies = ArrayList<MovieEntity>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
          savedInstanceState: Bundle?): View {
        fragmentMoviesBinding =
            FragmentMoviesBinding.inflate(layoutInflater, container, false)
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
            .navigate(R.id.action_homeFragment_to_detailMovieFragment, mBundle)
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
            val factory = ViewModelFactory.getInstance()
            val viewModel = ViewModelProvider(this, factory)[MoviesViewModel::class.java]
            showLoading(true)
            viewModel.getMovies().observe(viewLifecycleOwner, {
                if (it != null){
                    showMovieItems(it)
                    showLoading(false)
                }
            })
        }
    }

    private fun showMovieItems(movieItems: List<MovieEntity>?) {
        moviesAdapter.apply {
            setMovies(movieItems)
            notifyDataSetChanged()
        }
        when {
            movieItems?.size as Int == 0 ->
                fragmentMoviesBinding.groupEmptyItem.visibility = View.VISIBLE
            else -> fragmentMoviesBinding.groupEmptyItem.visibility = View.GONE
        }
    }

    private fun showMoviesList() {
        moviesAdapter = MoviesAdapter(movies, this)
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
}
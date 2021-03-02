package com.dicoding.academies.moviecataloguedatamanagement.ui.favorite

import android.os.Bundle
import android.view.*
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.academies.moviecataloguedatamanagement.R
import com.dicoding.academies.moviecataloguedatamanagement.data.source.local.entity.MovieEntity
import com.dicoding.academies.moviecataloguedatamanagement.databinding.FragmentFavoriteMoviesBinding
import com.dicoding.academies.moviecataloguedatamanagement.helper.SortUtils.ASCENDING
import com.dicoding.academies.moviecataloguedatamanagement.helper.SortUtils.DESCENDING
import com.dicoding.academies.moviecataloguedatamanagement.helper.SortUtils.NEWEST
import com.dicoding.academies.moviecataloguedatamanagement.helper.SortUtils.OLDEST
import com.dicoding.academies.moviecataloguedatamanagement.preference.SortPreference
import com.dicoding.academies.moviecataloguedatamanagement.ui.DetailMovieFragment.Companion.EXTRA_MOVIE_ID
import com.dicoding.academies.moviecataloguedatamanagement.ui.favorite.adapter.FavoriteMoviesAdapter
import com.dicoding.academies.moviecataloguedatamanagement.ui.favorite.callback.OnFavoriteMovieItemClickCallback
import com.dicoding.academies.moviecataloguedatamanagement.viewmodel.FavoriteMoviesViewModel
import com.dicoding.academies.moviecataloguedatamanagement.viewmodel.ViewModelFactory

class FavoriteMoviesFragment : Fragment(), OnFavoriteMovieItemClickCallback {

    private lateinit var fragmentFavoriteMoviesBinding: FragmentFavoriteMoviesBinding
    private lateinit var favoriteMoviesAdapter: FavoriteMoviesAdapter
    private lateinit var sortPreference: SortPreference
    private var sort = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
          savedInstanceState: Bundle?): View {
        fragmentFavoriteMoviesBinding =
            FragmentFavoriteMoviesBinding.inflate(layoutInflater, container, false)
        return fragmentFavoriteMoviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sortPreference = SortPreference()
        showFavoriteMoviesList()
        getViewModelLiveData(ASCENDING)
        loadSortItems()
        sortItems()
    }

    override fun onItemClicked(data: MovieEntity) {
        val mBundle = Bundle().apply { data.id?.let { putInt(EXTRA_MOVIE_ID, it) } }
        NavHostFragment
            .findNavController(this)
            .navigate(R.id.action_navigation_favorites_to_detailMovieFragment, mBundle)
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

    private fun loadSortItems() {
        fragmentFavoriteMoviesBinding.apply {
            rbSortByTitleAsc.isChecked = sortPreference.getSortMoviesByTitleAsc(requireContext())
            rbSortByTitleDesc.isChecked = sortPreference.getSortMoviesByTitleDesc(requireContext())
            rbSortFavMoviesByNewest.isChecked =
                sortPreference.getSortMoviesByNewest(requireContext())
            rbSortFavMoviesByOldest.isChecked =
                sortPreference.getSortMoviesByOldest(requireContext())
        }
        when {
            fragmentFavoriteMoviesBinding.rbSortByTitleAsc.isChecked -> sort = ASCENDING
            fragmentFavoriteMoviesBinding.rbSortByTitleDesc.isChecked -> sort = DESCENDING
            fragmentFavoriteMoviesBinding.rbSortFavMoviesByNewest.isChecked -> sort = NEWEST
            fragmentFavoriteMoviesBinding.rbSortFavMoviesByOldest.isChecked -> sort = OLDEST
        }
        getViewModelLiveData(sort)
    }

    private fun sortItems() {
        val checkedRadioButtonId =
            fragmentFavoriteMoviesBinding.rgSortFavMovieItems.checkedRadioButtonId
        if (checkedRadioButtonId != -1) {
            fragmentFavoriteMoviesBinding.rgSortFavMovieItems
                .setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    R.id.rb_sort_by_title_asc -> {
                        sort = ASCENDING
                        sortPreference.apply {
                            setSortMoviesByTitleAsc(requireContext())
                            clearSortMoviesByTitleDesc(requireContext())
                            clearSortMoviesByNewest(requireContext())
                            clearSortMoviesByOldest(requireContext())
                        }
                    }
                    R.id.rb_sort_by_title_desc -> {
                        sort = DESCENDING
                        sortPreference.apply {
                            setSortMoviesByTitleDesc(requireContext())
                            clearSortMoviesByTitleAsc(requireContext())
                            clearSortMoviesByNewest(requireContext())
                            clearSortMoviesByOldest(requireContext())
                        }
                    }
                    R.id.rb_sort_fav_movies_by_newest -> {
                        sort = NEWEST
                        sortPreference.apply {
                            setSortMoviesByNewest(requireContext())
                            clearSortMoviesByTitleAsc(requireContext())
                            clearSortMoviesByTitleDesc(requireContext())
                            clearSortMoviesByOldest(requireContext())
                        }
                    }
                    R.id.rb_sort_fav_movies_by_oldest -> {
                        sort = OLDEST
                        sortPreference.apply {
                            setSortMoviesByOldest(requireContext())
                            clearSortMoviesByTitleAsc(requireContext())
                            clearSortMoviesByTitleDesc(requireContext())
                            clearSortMoviesByNewest(requireContext())
                        }
                    }
                }
                getViewModelLiveData(sort)
            }
        }
    }

    private fun showFavoriteMovieItems(favoriteMovieItems: PagedList<MovieEntity>) {
        favoriteMoviesAdapter.submitList(favoriteMovieItems)
        when (favoriteMovieItems.size) {
            0 -> {
                fragmentFavoriteMoviesBinding.apply {
                    groupEmptyItemFav.visibility = View.VISIBLE
                    tvMovieItemsSize.visibility = View.GONE
                }
            }
            else -> {
                fragmentFavoriteMoviesBinding.tvMovieItemsSize.text = StringBuilder(
                        "${getString(R.string.favored_movie_items)}${favoriteMovieItems.loadedCount}")
                fragmentFavoriteMoviesBinding.tvMovieItemsSize.visibility = View.VISIBLE
                fragmentFavoriteMoviesBinding.groupEmptyItemFav.visibility = View.GONE
            }
        }
    }

    private fun showFavoriteMoviesList() {
        favoriteMoviesAdapter = FavoriteMoviesAdapter(this)
        with(fragmentFavoriteMoviesBinding.rvFavoriteMovie) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = favoriteMoviesAdapter
        }
    }

    private fun getViewModelLiveData(sort: String) {
        if (parentFragment != null) {
            val factory = ViewModelFactory.getInstance(requireContext())
            val viewModel =
                ViewModelProvider(this, factory)[FavoriteMoviesViewModel::class.java]
            showLoading(true)
            viewModel.getFavoriteMovies(sort).observe(viewLifecycleOwner, {
                if (it != null) {
                    showFavoriteMovieItems(it)
                    showLoading(false)
                }
            })
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) fragmentFavoriteMoviesBinding.pbRvFavoriteMovie.visibility = View.VISIBLE
        else fragmentFavoriteMoviesBinding.pbRvFavoriteMovie.visibility = View.GONE
    }
}
package com.dicoding.academies.moviecataloguedatamanagement.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.academies.moviecataloguedatamanagement.R
import com.dicoding.academies.moviecataloguedatamanagement.data.source.local.entity.MovieEntity
import com.dicoding.academies.moviecataloguedatamanagement.databinding.FragmentDetailMovieBinding
import com.dicoding.academies.moviecataloguedatamanagement.viewmodel.DetailMovieViewModel
import com.dicoding.academies.moviecataloguedatamanagement.viewmodel.ViewModelFactory
import com.dicoding.academies.moviecataloguedatamanagement.vo.Status
import com.google.android.material.bottomnavigation.BottomNavigationView

class DetailMovieFragment : Fragment() {

    companion object {
        const val EXTRA_MOVIE_ID = "extra_movie_id"
    }
    private lateinit var fragmentDetailMovieBinding: FragmentDetailMovieBinding
    private lateinit var viewModel: DetailMovieViewModel
    private var menu: Menu? = null
    private var movieId: Int? = 0
    private var titleData = ""
    private var yearData = ""
    private var genresData = ""
    private var tagLineData = ""
    private var scoreData = ""
    private var releaseDateData = ""
    private var popularityData = ""
    private var overviewData = ""
    private var productionCompaniesData = ""
    private val TAG = DetailMovieFragment::class.java.simpleName

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentDetailMovieBinding =
            FragmentDetailMovieBinding.inflate(layoutInflater, container, false)
        return fragmentDetailMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setActionBar()
        setHasOptionsMenu(true)
        val navView: BottomNavigationView = requireActivity().findViewById(R.id.nav_view)
        navView.visibility = View.GONE
        getDetailMovieViewModelLive()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        viewModel.detailMovie.observe(this, {
            if (it != null) {
                when (it.status) {
                    Status.LOADING -> showLoading(true)
                    Status.SUCCESS -> if (it.data != null) {
                        val state = it.data.isFavorite
                        setFavoriteState(state)
                        showLoading(false)
                    }
                    Status.ERROR -> {
                        showLoading(false)
                        showErrorAlert()
                    }
                }
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onNavigateUp(item.itemId)
        selectDetailMenu(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setActionBar() {
        (activity as? AppCompatActivity?)?.supportActionBar?.apply {
            setHomeButtonEnabled(true)
            setDisplayHomeAsUpEnabled(true)
            title = getString(R.string.detail_movie_title)
        }
    }

    private fun onNavigateUp(itemId: Int) {
        if (itemId == android.R.id.home) requireActivity().onBackPressed()
    }

    private fun getDetailMovieViewModelLive() {
        val factory = context?.let { ViewModelFactory.getInstance(it) }
        viewModel = ViewModelProvider(this,
            factory as ViewModelProvider.Factory)[DetailMovieViewModel::class.java]
        if (arguments != null) {
            movieId = arguments?.getInt(EXTRA_MOVIE_ID, 0) as Int
            if (movieId != null) {
                showLoading(true)
                viewModel.apply {
                    setSelectedMovie(movieId as Int)
                    detailMovie.observe(viewLifecycleOwner, {
                        if (it != null) {
                            when (it.status) {
                                Status.LOADING -> showLoading(true)
                                Status.SUCCESS -> {
                                    populateDetailMovie(it.data as MovieEntity)
                                    showLoading(false)
                                }
                                Status.ERROR -> {
                                    showLoading(false)
                                    showErrorAlert()
                                }
                            }
                        }
                        Log.d(TAG, "${it.data}")
                    })
                }
            }
        }
    }

    private fun populateDetailMovie(detailMovieEntity: MovieEntity) {
        fragmentDetailMovieBinding.apply {
            titleData = detailMovieEntity.title.toString()
            tvItemMovieTitleReceived.text = titleData
            yearData = detailMovieEntity.releaseDate.toString().take(4)
            tvItemYearReceived.text = yearData
            genresData = detailMovieEntity.genres.toString()
            tvItemGenresReceived.text = genresData
            tagLineData = detailMovieEntity.tagLine.toString()
            tvItemTagLineReceived.text = tagLineData
            scoreData = detailMovieEntity.score.toString()
            tvItemScoreReceived.text = StringBuilder("$scoreData%")
            releaseDateData = detailMovieEntity.releaseDate.toString()
            tvItemReleaseDateReceived.text = releaseDateData
            popularityData = detailMovieEntity.popularity.toString()
            tvItemPopularityReceived.text = popularityData
            overviewData = detailMovieEntity.overview.toString()
            tvItemOverviewReceived.text = overviewData
            productionCompaniesData = if (detailMovieEntity.productionCompanies.isNullOrBlank()) "-"
            else detailMovieEntity.productionCompanies
            tvItemProductionCompaniesReceived.text = productionCompaniesData
            Glide.with(this@DetailMovieFragment)
                .load("${getString(R.string.poster_base_url)}${detailMovieEntity.posterPath}")
                .apply(RequestOptions.placeholderOf(R.drawable.ic_image_loading))
                .error(R.drawable.ic_image_error)
                .into(rivItemPosterReceived)
        }
    }

    private fun selectDetailMenu(selectedMenu: Int) {
        if (selectedMenu == R.id.app_bar_share) {
            val shareMovie = "${getString(R.string.app_title)}:\n" +
                "${getString(R.string.link)}: ${getString(R.string.movie_base_url)}${movieId}\n" +
                "${getString(R.string.movie_title)}: $titleData\n" +
                "${getString(R.string.year)}: $yearData\n" +
                "${getString(R.string.release_date)}: $releaseDateData\n" +
                "${getString(R.string.genre)}: $genresData\n" +
                "${getString(R.string.popularity)}: $popularityData\n" +
                "${getString(R.string.user_score)}: $scoreData\n" +
                "${getString(R.string.tag_line)}: $tagLineData\n" +
                "${getString(R.string.overview)}: $overviewData\n" +
                "${getString(R.string.production_companies)}: $productionCompaniesData"
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder
                .from(requireActivity())
                .setType(mimeType)
                .setChooserTitle(getString(R.string.share_movie_title))
                .setText(shareMovie)
                .startChooser()
        } else if (selectedMenu == R.id.app_bar_favorite) viewModel.setFavorite()
    }

    private fun setFavoriteState(state: Boolean) {
        if (menu == null) return
        val menuFavorite = menu?.findItem(R.id.app_bar_favorite)
        if (state)
            menuFavorite?.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_favored)
        else menuFavorite?.icon =
            context?.let { ContextCompat.getDrawable(it, R.drawable.ic_favorite) }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            fragmentDetailMovieBinding.apply {
                clTop.visibility = View.GONE
                clCenter.visibility = View.GONE
                clBottom.visibility = View.GONE
                pbDetailMovie.visibility = View.VISIBLE
            }
        } else {
            fragmentDetailMovieBinding.apply {
                clTop.visibility = View.VISIBLE
                clCenter.visibility = View.VISIBLE
                clBottom.visibility = View.VISIBLE
                pbDetailMovie.visibility = View.GONE
            }
        }
    }

    private fun showErrorAlert() {
        val dialogTitle = getString(R.string.loading_failed_alert)
        val dialogMsg = Status.ERROR.name
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder
            .setTitle(dialogTitle)
            .setMessage(dialogMsg)
            .setPositiveButton(getString(R.string.ok_positive_btn)) { dialog, _ -> dialog.cancel() }
        val errorAlert = alertDialogBuilder.create()
        if (errorAlert.isShowing) errorAlert.cancel()
        else errorAlert.show()
    }
}
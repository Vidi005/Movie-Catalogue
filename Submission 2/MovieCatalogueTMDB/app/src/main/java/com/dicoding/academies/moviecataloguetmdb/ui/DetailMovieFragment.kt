package com.dicoding.academies.moviecataloguetmdb.ui

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.academies.moviecataloguetmdb.R
import com.dicoding.academies.moviecataloguetmdb.data.source.local.entity.DetailMovieEntity
import com.dicoding.academies.moviecataloguetmdb.databinding.FragmentDetailMovieBinding
import com.dicoding.academies.moviecataloguetmdb.viewmodel.DetailMovieViewModel
import com.dicoding.academies.moviecataloguetmdb.viewmodel.ViewModelFactory

class DetailMovieFragment : Fragment() {

    companion object {
        const val EXTRA_MOVIE_ID = "extra_movie_id"
    }
    private lateinit var fragmentDetailMovieBinding: FragmentDetailMovieBinding
    private var movieId: Int? = null
    private lateinit var titleData: String
    private lateinit var yearData: String
    private lateinit var genresData: String
    private lateinit var tagLineData: String
    private lateinit var scoreData: String
    private lateinit var releaseDateData: String
    private lateinit var popularityData: String
    private lateinit var overviewData: String
    private lateinit var productionCompaniesData: String

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
        getDetailMovieViewModelLive()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_share, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onNavigateUp(item.itemId)
        shareDetailMovie(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setActionBar() {
        (activity as AppCompatActivity?)?.supportActionBar?.apply {
            setHomeButtonEnabled(true)
            setDisplayHomeAsUpEnabled(true)
            title = getString(R.string.detail_movie_title)
        }
    }

    private fun onNavigateUp(itemId: Int) {
        if (itemId == android.R.id.home) requireActivity().onBackPressed()
    }

    private fun getDetailMovieViewModelLive() {
        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[DetailMovieViewModel::class.java]
        if (arguments != null) {
            movieId = arguments?.getInt(EXTRA_MOVIE_ID, 0) as Int
            if (movieId != null) {
                showLoading(true)
                viewModel.apply {
                    setSelectedMovie(movieId as Int)
                    getDetailMovie().observe(viewLifecycleOwner, {
                        if (it != null) {
                            populateDetailMovie(it)
                            showLoading(false)
                        }
                    })
                }
            }
        }
    }

    private fun populateDetailMovie(detailMovieEntity: DetailMovieEntity) {
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

    private fun shareDetailMovie(sharedDetailMovie: Int) {
        if (sharedDetailMovie == R.id.app_bar_share) {
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
        }
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
}
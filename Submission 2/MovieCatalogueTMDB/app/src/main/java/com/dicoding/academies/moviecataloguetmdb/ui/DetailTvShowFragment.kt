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
import com.dicoding.academies.moviecataloguetmdb.data.source.local.entity.DetailTVShowEntity
import com.dicoding.academies.moviecataloguetmdb.databinding.FragmentDetailTvShowBinding
import com.dicoding.academies.moviecataloguetmdb.viewmodel.DetailTVShowViewModel
import com.dicoding.academies.moviecataloguetmdb.viewmodel.ViewModelFactory

class DetailTvShowFragment : Fragment() {

    companion object {
        const val EXTRA_TV_SHOW_ID = "extra_tv_show_id"
    }
    private lateinit var fragmentDetailTvShowBinding: FragmentDetailTvShowBinding
    private var tvId: Int? = null
    private lateinit var nameData: String
    private lateinit var firstAirDateData: String
    private lateinit var genresData: String
    private lateinit var tagLineData: String
    private lateinit var scoreData: String
    private lateinit var popularityData: String
    private lateinit var overviewData: String
    private lateinit var productionCompaniesData: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentDetailTvShowBinding =
            FragmentDetailTvShowBinding.inflate(layoutInflater, container, false)
        return fragmentDetailTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setActionBar()
        setHasOptionsMenu(true)
        getDetailTvShowViewModelLive()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_share, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onNavigateUp(item.itemId)
        shareDetailTvShow(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setActionBar() {
        (activity as AppCompatActivity?)?.supportActionBar?.apply {
            setHomeButtonEnabled(true)
            setDisplayHomeAsUpEnabled(true)
            title = getString(R.string.detail_tv_show_title)
        }
    }

    private fun onNavigateUp(itemId: Int) {
        if (itemId == android.R.id.home) requireActivity().onBackPressed()
    }

    private fun getDetailTvShowViewModelLive() {
        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[DetailTVShowViewModel::class.java]
        if (arguments != null) {
            tvId = arguments?.getInt(EXTRA_TV_SHOW_ID, 0)
            if (tvId != null) {
                showLoading(true)
                viewModel.apply {
                    setSelectedTVShow(tvId as Int)
                    getDetailTVShow().observe(viewLifecycleOwner, {
                        if (it != null) {
                            populateDetailMovie(it)
                            showLoading(false)
                        }
                    })
                }
            }
        }
    }

    private fun populateDetailMovie(detailTVShowEntity: DetailTVShowEntity) {
        fragmentDetailTvShowBinding.apply {
            nameData = detailTVShowEntity.name.toString()
            tvItemTvShowNameReceived.text = nameData
            firstAirDateData = detailTVShowEntity.firstAirDate.toString()
            tvItemFirstAirDateReceived.text = firstAirDateData
            genresData = detailTVShowEntity.genres.toString()
            tvItemGenresReceived.text = genresData
            tagLineData = detailTVShowEntity.tagLine.toString()
            tvItemTagLineReceived.text = tagLineData
            scoreData = detailTVShowEntity.score.toString()
            tvItemScoreReceived.text = StringBuilder("$scoreData%")
            popularityData = detailTVShowEntity.popularity.toString()
            tvItemPopularityReceived.text = popularityData
            overviewData = detailTVShowEntity.overview.toString()
            tvItemOverviewReceived.text = overviewData
            productionCompaniesData = if (detailTVShowEntity.productionCompanies.isNullOrBlank()) "-"
            else detailTVShowEntity.productionCompanies
            tvItemProductionCompaniesReceived.text = productionCompaniesData
            Glide.with(this@DetailTvShowFragment)
                .load("${getString(R.string.poster_base_url)}${detailTVShowEntity.posterPath}")
                .apply(RequestOptions.placeholderOf(R.drawable.ic_image_loading))
                .error(R.drawable.ic_image_error)
                .into(rivItemPosterReceived)
        }
    }

    private fun shareDetailTvShow(sharedTvShow: Int) {
        if (sharedTvShow == R.id.app_bar_share) {
            val shareMovie = "${getString(R.string.app_title)}:\n" +
                "${getString(R.string.link)}: ${getString(R.string.tv_show_base_url)}${tvId}\n" +
                "${getString(R.string.movie_title)}: $nameData\n" +
                "${getString(R.string.first_air_date)}: $firstAirDateData\n" +
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
            fragmentDetailTvShowBinding.apply {
                clTop.visibility = View.GONE
                clCenter.visibility = View.GONE
                clBottom.visibility = View.GONE
                pbDetailTvShow.visibility = View.VISIBLE
            }
        } else {
            fragmentDetailTvShowBinding.apply {
                clTop.visibility = View.VISIBLE
                clCenter.visibility = View.VISIBLE
                clBottom.visibility = View.VISIBLE
                pbDetailTvShow.visibility = View.GONE
            }
        }
    }
}
package com.dicoding.academies.moviecataloguedatamanagement.ui

import android.content.Context
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
import com.dicoding.academies.moviecataloguedatamanagement.data.source.local.entity.TVShowEntity
import com.dicoding.academies.moviecataloguedatamanagement.databinding.FragmentDetailTvShowBinding
import com.dicoding.academies.moviecataloguedatamanagement.viewmodel.DetailTVShowViewModel
import com.dicoding.academies.moviecataloguedatamanagement.viewmodel.ViewModelFactory
import com.dicoding.academies.moviecataloguedatamanagement.vo.Status
import com.google.android.material.bottomnavigation.BottomNavigationView

class DetailTvShowFragment : Fragment() {

    companion object {
        const val EXTRA_TV_SHOW_ID = "extra_tv_show_id"
    }
    private lateinit var fragmentDetailTvShowBinding: FragmentDetailTvShowBinding
    private lateinit var viewModel: DetailTVShowViewModel
    private var menu: Menu? = null
    private var tvId: Int? = 0
    private var nameData = ""
    private var firstAirDateData = ""
    private var genresData = ""
    private var tagLineData = ""
    private var scoreData = ""
    private var popularityData = ""
    private var overviewData = ""
    private var productionCompaniesData = ""
    private val TAG = DetailTvShowFragment::class.java.simpleName

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
        val navView: BottomNavigationView = requireActivity().findViewById(R.id.nav_view)
        navView.visibility = View.GONE
        getDetailTvShowViewModelLive()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        viewModel.detailTvShow.observe(this, {
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
            title = getString(R.string.detail_tv_show_title)
        }
    }

    private fun onNavigateUp(itemId: Int) {
        if (itemId == android.R.id.home) requireActivity().onBackPressed()
    }

    private fun getDetailTvShowViewModelLive() {
        val factory = ViewModelFactory.getInstance(activity as Context)
        viewModel = ViewModelProvider(this, factory)[DetailTVShowViewModel::class.java]
        if (arguments != null) {
            tvId = arguments?.getInt(EXTRA_TV_SHOW_ID, 0)
            if (tvId != null) {
                showLoading(true)
                viewModel.apply {
                    setSelectedTVShow(tvId as Int)
                    detailTvShow.observe(viewLifecycleOwner, {
                        if (it != null) {
                            when (it.status) {
                                Status.LOADING -> showLoading(true)
                                Status.SUCCESS -> {
                                    populateDetailMovie(it.data as TVShowEntity)
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

    private fun populateDetailMovie(detailTVShowEntity: TVShowEntity) {
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

    private fun selectDetailMenu(selectedMenu: Int) {
        if (selectedMenu == R.id.app_bar_share) {
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

    private fun showErrorAlert() {
        val dialogTitle = getString(R.string.loading_failed_alert)
        val dialogMsg = Status.ERROR.name
        val alertDialogBuilder = AlertDialog.Builder(requireActivity())
        alertDialogBuilder
            .setTitle(dialogTitle)
            .setMessage(dialogMsg)
            .setPositiveButton(getString(R.string.ok_positive_btn)) { dialog, _ -> dialog.cancel() }
        val errorAlert = alertDialogBuilder.create()
        if (errorAlert.isShowing) errorAlert.cancel()
        else errorAlert.show()
    }
}
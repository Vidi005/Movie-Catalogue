package com.dicoding.picodiploma.moviecatalogue.ui

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.moviecatalogue.R
import com.dicoding.picodiploma.moviecatalogue.data.TVShowEntity
import com.dicoding.picodiploma.moviecatalogue.databinding.FragmentDetailTvShowBinding
import com.dicoding.picodiploma.moviecatalogue.viewmodel.DetailTvShowViewModel

class DetailTvShowFragment : Fragment() {

    private lateinit var fragmentDetailTvShowBinding: FragmentDetailTvShowBinding
    private lateinit var tvShowNameData: String
    private lateinit var yearData: String
    private lateinit var genreData: String
    private lateinit var tagLineData: String
    private lateinit var scoreData: String
    private lateinit var durationData: String
    private lateinit var overviewData: String
    private lateinit var personData: String
    companion object {
        const val EXTRA_TV_SHOW_NAME = "extra_tv_show_name"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
          savedInstanceState: Bundle?): View? {
        fragmentDetailTvShowBinding =
                FragmentDetailTvShowBinding.inflate(layoutInflater, container, false)
        return fragmentDetailTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setActionBar()
        setHasOptionsMenu(true)
        getDetailTvShowViewModel()
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

    private fun getDetailTvShowViewModel() {
        val viewModel = ViewModelProvider(this,
            ViewModelProvider.NewInstanceFactory())[DetailTvShowViewModel::class.java]
        if (arguments != null) {
            val tvShowName = arguments?.getString(EXTRA_TV_SHOW_NAME)
            if (tvShowName != null) {
                viewModel.setSelectedTvShow(tvShowName)
                populateDetailTvShow(viewModel.getDetailTvShow())
            }
        }
    }

    private fun populateDetailTvShow(tvShowEntity: TVShowEntity) {
        fragmentDetailTvShowBinding.apply {
            tvShowNameData = tvShowEntity.tvShowName.toString()
            tvItemTvShowNameReceived.text = tvShowNameData
            yearData = tvShowEntity.year.toString()
            tvItemYearReceived.text = yearData
            genreData = tvShowEntity.genre.toString()
            tvItemGenreReceived.text = genreData
            tagLineData = tvShowEntity.tagLine.toString()
            tvItemTagLineReceived.text = tagLineData
            scoreData = tvShowEntity.score.toString()
            tvItemScoreReceived.text = StringBuilder("$scoreData%")
            durationData = tvShowEntity.duration.toString()
            tvItemDurationReceived.text = durationData
            overviewData = tvShowEntity.overview.toString()
            tvItemOverviewReceived.text = overviewData
            personData = tvShowEntity.person.toString()
            tvItemPersonReceived.text = personData
            Glide.with(this@DetailTvShowFragment)
                .load(tvShowEntity.poster)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_image_loading))
                .error(R.drawable.ic_image_error)
                .into(rivItemPosterReceived)
        }
    }

    private fun shareDetailTvShow(sharedTvShow: Int) {
        if (sharedTvShow == R.id.app_bar_share) {
            val shareMovie = "${getString(R.string.app_title)}:\n" +
                "${getString(R.string.movie_title)}: $tvShowNameData\n" +
                "${getString(R.string.year)}: $yearData\n" +
                "${getString(R.string.genre)}: $genreData\n" +
                "${getString(R.string.duration)}: $durationData\n" +
                "${getString(R.string.user_score)}: $scoreData\n" +
                "${getString(R.string.tag_line)}: $tagLineData\n" +
                "${getString(R.string.overview)}: $overviewData\n" +
                "${getString(R.string.persons)}: $personData"
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder
                .from(requireActivity())
                .setType(mimeType)
                .setChooserTitle(getString(R.string.share_movie_title))
                .setText(shareMovie)
                .startChooser()
        }
    }
}

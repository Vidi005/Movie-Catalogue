package com.dicoding.academies.moviecataloguedatamanagement.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.academies.moviecataloguedatamanagement.R
import com.dicoding.academies.moviecataloguedatamanagement.data.source.local.entity.TVShowEntity
import com.dicoding.academies.moviecataloguedatamanagement.databinding.FragmentFavoriteTvShowsBinding
import com.dicoding.academies.moviecataloguedatamanagement.helper.SortUtils.ASCENDING
import com.dicoding.academies.moviecataloguedatamanagement.helper.SortUtils.DESCENDING
import com.dicoding.academies.moviecataloguedatamanagement.helper.SortUtils.NEWEST
import com.dicoding.academies.moviecataloguedatamanagement.helper.SortUtils.OLDEST
import com.dicoding.academies.moviecataloguedatamanagement.preference.SortPreference
import com.dicoding.academies.moviecataloguedatamanagement.ui.DetailTvShowFragment.Companion.EXTRA_TV_SHOW_ID
import com.dicoding.academies.moviecataloguedatamanagement.ui.favorite.adapter.FavoriteTVShowsAdapter
import com.dicoding.academies.moviecataloguedatamanagement.ui.favorite.callback.OnFavoriteTVShowItemClickCallback
import com.dicoding.academies.moviecataloguedatamanagement.viewmodel.FavoriteTVShowsViewModel
import com.dicoding.academies.moviecataloguedatamanagement.viewmodel.ViewModelFactory

class FavoriteTvShowsFragment : Fragment(), OnFavoriteTVShowItemClickCallback {

    private lateinit var fragmentFavoriteTvShowsBinding: FragmentFavoriteTvShowsBinding
    private lateinit var favoriteTVShowsAdapter: FavoriteTVShowsAdapter
    private lateinit var sortPreference: SortPreference
    private var sort = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
          savedInstanceState: Bundle?): View {
        fragmentFavoriteTvShowsBinding =
            FragmentFavoriteTvShowsBinding.inflate(layoutInflater, container, false)
        return fragmentFavoriteTvShowsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sortPreference = SortPreference()
        showFavoriteTvShowsList()
        getViewModelLiveData(ASCENDING)
        loadSortItems()
        sortItems()
    }

    override fun onItemClicked(data: TVShowEntity) {
        val mBundle = Bundle().apply { data.id?.let { putInt(EXTRA_TV_SHOW_ID, it) } }
        NavHostFragment
            .findNavController(this)
            .navigate(R.id.action_navigation_favorites_to_detailTvShowFragment, mBundle)
    }

    override fun onItemShared(data: TVShowEntity) {
        val shareTvShow = "${getString(R.string.app_title)}:\n" +
            "${getString(R.string.link)}: ${getString(R.string.tv_show_base_url)}${data.id}\n" +
            "${getString(R.string.tv_show_name)}: ${data.name}\n" +
            "${getString(R.string.overview)}: ${data.overview}\n" +
            "${getString(R.string.first_air_date)}: ${data.firstAirDate}"
        val mimeType = "text/plain"
        ShareCompat.IntentBuilder
            .from(requireActivity())
            .setType(mimeType)
            .setChooserTitle(getString(R.string.share_movie_title))
            .setText(shareTvShow)
            .startChooser()
    }

    private fun loadSortItems() {
        fragmentFavoriteTvShowsBinding.apply {
            rbSortByNameAsc.isChecked = sortPreference.getSortTvShowsByNameAsc(requireContext())
            rbSortByNameDesc.isChecked = sortPreference.getSortTvShowsByNameDesc(requireContext())
            rbSortFavTvShowsByNewest.isChecked =
                sortPreference.getSortTvShowsByNewest(requireContext())
            rbSortFavTvShowsByOldest.isChecked =
                sortPreference.getSortTvShowsByOldest(requireContext())
        }
        when {
            fragmentFavoriteTvShowsBinding.rbSortByNameAsc.isChecked -> sort = ASCENDING
            fragmentFavoriteTvShowsBinding.rbSortByNameDesc.isChecked -> sort = DESCENDING
            fragmentFavoriteTvShowsBinding.rbSortFavTvShowsByNewest.isChecked -> sort = NEWEST
            fragmentFavoriteTvShowsBinding.rbSortFavTvShowsByOldest.isChecked -> sort = OLDEST
        }
        getViewModelLiveData(sort)
    }

    private fun sortItems() {
        val checkedRadioButtonId =
            fragmentFavoriteTvShowsBinding.rgSortFavTvShowItems.checkedRadioButtonId
        if (checkedRadioButtonId != -1) {
            fragmentFavoriteTvShowsBinding.rgSortFavTvShowItems
                .setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    R.id.rb_sort_by_name_asc -> {
                        sort = ASCENDING
                        sortPreference.apply {
                            setSortTvShowsByNameAsc(requireContext())
                            clearSortTvShowsByNameDesc(requireContext())
                            clearSortTvShowsByNewest(requireContext())
                            clearSortTvShowsByOldest(requireContext())
                        }
                    }
                    R.id.rb_sort_by_title_desc -> {
                        sort = DESCENDING
                        sortPreference.apply {
                            setSortTvShowsByNameDesc(requireContext())
                            clearSortTvShowsByNameAsc(requireContext())
                            clearSortTvShowsByNewest(requireContext())
                            clearSortTvShowsByOldest(requireContext())
                        }
                    }
                    R.id.rb_sort_fav_tv_shows_by_newest -> {
                        sort = NEWEST
                        sortPreference.apply {
                            setSortTvShowsByNewest(requireContext())
                            clearSortTvShowsByNameAsc(requireContext())
                            clearSortTvShowsByNameDesc(requireContext())
                            clearSortTvShowsByOldest(requireContext())
                        }
                    }
                    R.id.rb_sort_fav_tv_shows_by_oldest -> {
                        sort = OLDEST
                        sortPreference.apply {
                            setSortTvShowsByOldest(requireContext())
                            clearSortTvShowsByNameAsc(requireContext())
                            clearSortTvShowsByNameDesc(requireContext())
                            clearSortTvShowsByNewest(requireContext())
                        }
                    }
                }
                getViewModelLiveData(sort)
            }
        }
    }

    private fun showFavoriteTvShowsList() {
        favoriteTVShowsAdapter = FavoriteTVShowsAdapter(this)
        with(fragmentFavoriteTvShowsBinding.rvFavoriteTvShow) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = favoriteTVShowsAdapter
        }
    }

    private fun showFavoriteTvShowItems(favoriteTvShowItems: PagedList<TVShowEntity>) {
        favoriteTVShowsAdapter.submitList(favoriteTvShowItems)
        when (favoriteTvShowItems.size) {
            0 -> {
                fragmentFavoriteTvShowsBinding.tvTvShowItemsSize.visibility = View.GONE
                fragmentFavoriteTvShowsBinding.groupEmptyItemFav.visibility = View.VISIBLE
            }
            else -> fragmentFavoriteTvShowsBinding.apply {
                groupEmptyItemFav.visibility = View.GONE
                tvTvShowItemsSize.apply {
                    text = StringBuilder(
                    "${getString(R.string.favored_movie_items)}${favoriteTvShowItems.lastIndex + 1}")
                    visibility = View.VISIBLE
                }
            }
        }
    }

    private fun getViewModelLiveData(sort: String) {
        if (parentFragment != null) {
            val factory = ViewModelFactory.getInstance(requireContext())
            val viewModel =
                ViewModelProvider(this, factory)[FavoriteTVShowsViewModel::class.java]
            showLoading(true)
            viewModel.getFavoriteTVShows(sort).observe(viewLifecycleOwner, {
                if (it != null) {
                    showFavoriteTvShowItems(it)
                    showLoading(false)
                }
            })
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) fragmentFavoriteTvShowsBinding.pbRvFavoriteTvShow.visibility = View.VISIBLE
        else fragmentFavoriteTvShowsBinding.pbRvFavoriteTvShow.visibility = View.GONE
    }
}
package com.elnemr.movieflix.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
import com.elnemr.movieflix.R
import com.elnemr.movieflix.databinding.FragmentAllMoviesBinding
import com.elnemr.movieflix.domain.model.Movie
import com.elnemr.movieflix.presentation.adapter.base.BasePaginationAdapter
import com.elnemr.movieflix.presentation.adapter.movie.MoviesAdapter
import com.elnemr.movieflix.presentation.adapter.movie.OnMovieClickInterface
import com.elnemr.movieflix.presentation.ui.base.BaseFragment
import com.elnemr.movieflix.presentation.viewmodel.MoviesViewModel
import com.elnemr.movieflix.presentation.viewmodel.state.MoviesViewModelState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AllMoviesFragment : BaseFragment(R.layout.fragment_all_movies),
    OnMovieClickInterface {

    private val mAdapter: BasePaginationAdapter<Movie> = MoviesAdapter(this)
    private val viewModel by viewModels<MoviesViewModel>()

    private lateinit var binding: FragmentAllMoviesBinding

    private val selectedGenre: Int? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAllMoviesBinding.bind(view)

        initPostponeTransition(view, this)

        initRecyclerView()
        fetchMovies(selectedGenre)
        binding.refresh.let {
            it.setOnRefreshListener {
                binding.rvMovies.showShimmer()
                it.isRefreshing = false
                fetchMovies(selectedGenre)
            }
        }

    }

    private fun initRecyclerView() {
        binding.rvMovies.apply {
            adapter = mAdapter
            showShimmer()
        }
        mAdapter.stateRestorationPolicy = PREVENT_WHEN_EMPTY
    }

    private fun fetchMovies(genreId: Int? = null) {
        val params: HashMap<String, String> = hashMapOf()
        genreId?.let {
            params["with_genres"] = it.toString()
        }

        viewModel.fetchMovies(params)
    }

    override fun setUpViewModelStateObservers() {
        lifecycleScope.launchWhenCreated {
            viewModel.getStateFlow().buffer().collect {
                onStateChange(it)
            }
        }
    }

    private fun onStateChange(state: MoviesViewModelState) {
        when (state) {
            is MoviesViewModelState.OnMoviesFetched -> onMoviesFetched(state.response)
        }
    }

    private fun onMoviesFetched(response: PagingData<Movie>) {
        if (::binding.isInitialized)
            binding.rvMovies.hideShimmer()

        lifecycleScope.launchWhenCreated {
            mAdapter.submitData(response)
            withContext(Dispatchers.Main) {
                toggleViews(mAdapter.itemCount == 0)
            }
        }
    }


    override fun onDetailsClicked(data: Movie, view: View) {
        val extras = FragmentNavigatorExtras(view to view.transitionName)
        val action =
            AllMoviesFragmentDirections.actionAllMoviesFragmentToMovieDetailsFragment(
                view.transitionName,
                data
            )
        findNavController().navigate(action, extras)
    }


    private fun toggleViews(
        noData: Boolean
    ) {
        binding.tvNoMovies?.isVisible = noData
        binding.rvMovies?.isVisible = !noData
    }
}
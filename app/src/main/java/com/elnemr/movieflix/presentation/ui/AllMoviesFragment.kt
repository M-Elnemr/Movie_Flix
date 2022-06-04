package com.elnemr.movieflix.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
import com.elnemr.movieflix.R
import com.elnemr.movieflix.databinding.FragmentAllMoviesBinding
import com.elnemr.core.domain.model.Genre
import com.elnemr.core.domain.model.GenresResponse
import com.elnemr.core.domain.model.Movie
import com.elnemr.core.domain.result.NetworkResult
import com.elnemr.movieflix.presentation.adapter.base.BaseAdapter
import com.elnemr.movieflix.presentation.adapter.base.BasePaginationAdapter
import com.elnemr.movieflix.presentation.adapter.genre.GenreAdapter
import com.elnemr.movieflix.presentation.adapter.genre.OnGenreClickInterface
import com.elnemr.movieflix.presentation.adapter.movie.MoviesAdapter
import com.elnemr.movieflix.presentation.adapter.movie.OnMovieClickInterface
import com.elnemr.movieflix.presentation.ui.base.BaseFragment
import com.elnemr.movieflix.presentation.viewmodel.MoviesViewModel
import com.elnemr.movieflix.presentation.viewmodel.state.MoviesViewModelState
import kotlinx.coroutines.flow.buffer

class AllMoviesFragment : BaseFragment(R.layout.fragment_all_movies),
    OnMovieClickInterface, OnGenreClickInterface {
    private val movieAdapter: BasePaginationAdapter<Movie> = MoviesAdapter(this)
    private val genreAdapter: BaseAdapter<Genre> = GenreAdapter(this)
    private val viewModel by viewModels<MoviesViewModel>()

    private lateinit var binding: FragmentAllMoviesBinding

    private var selectedGenre: Int? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAllMoviesBinding.bind(view)

        initPostponeTransition(view, this)
        initRecyclerView()
        binding.refresh.let {
            it.setOnRefreshListener {
                binding.rvMovies.showShimmer()
                it.isRefreshing = false
                fetchMovies(selectedGenre)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchGenres()
        fetchMovies(selectedGenre)
    }
    
    private fun fetchGenres() {
        viewModel.fetchGenres()
    }

    private fun initRecyclerView() {
        binding.rvMovies.apply {
            adapter = movieAdapter
            if (movieAdapter.itemCount == 0) showShimmer()
        }

        binding.rvGenre.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            isNestedScrollingEnabled = false
            adapter = genreAdapter
        }

        movieAdapter.stateRestorationPolicy = PREVENT_WHEN_EMPTY
        genreAdapter.stateRestorationPolicy = PREVENT_WHEN_EMPTY
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
            is MoviesViewModelState.OnGenresFetched -> onGenresFetched(state.response)
        }
    }

    private fun onGenresFetched(response: NetworkResult<GenresResponse>) {
        when (response) {
            is NetworkResult.Empty -> {
            }
            is NetworkResult.Loading -> {
            }
            is NetworkResult.NetworkError -> {
            }
            is NetworkResult.ServerError -> {
            }
            is NetworkResult.Success -> response.data?.let {
                val genreList: ArrayList<Genre> = response.data?.genres as ArrayList<Genre>
                genreList.add(0, Genre("All", null))
                genreAdapter.setDataList(genreList)
            }
        }
    }

    private fun onMoviesFetched(response: PagingData<Movie>) {
        if (::binding.isInitialized)
            binding.rvMovies.hideShimmer()

        lifecycleScope.launchWhenCreated {
            movieAdapter.submitData(response)
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

    override fun onGenreClicked(genreId: Int?) {
        selectedGenre = genreId
        fetchMovies(selectedGenre)
    }
}
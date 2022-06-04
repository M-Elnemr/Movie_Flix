package com.elnemr.movieflix.presentation.ui

import android.os.Bundle
import android.view.View
import com.elnemr.movieflix.R
import com.elnemr.movieflix.databinding.FragmentMovieDetailsBinding
import com.elnemr.core.domain.model.Movie
import com.elnemr.movieflix.presentation.ui.base.BaseFragment

class MovieDetailsFragment : BaseFragment(R.layout.fragment_movie_details) {
    private lateinit var binding: FragmentMovieDetailsBinding
    private lateinit var movie: Movie

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieDetailsBinding.bind(view)

        handleArgs()
    }

    private fun handleArgs() {
        val args = MovieDetailsFragmentArgs.fromBundle(requireArguments())
        movie = args.movie
        initSharedTransition(binding.ivMovie, args.transName, this,
            movie.posterPath ?: "")

        loadDataIntoViews()
    }

    private fun loadDataIntoViews() {
        binding.title.text = movie.title
        binding.voteAverage.text = movie.voteAverage.toString()
        binding.originalLanguage.text = movie.originalLanguage
        binding.date.text = movie.releaseDate
    }

}
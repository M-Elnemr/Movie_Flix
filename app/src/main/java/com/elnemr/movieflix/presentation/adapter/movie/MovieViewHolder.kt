package com.elnemr.movieflix.presentation.adapter.movie

import coil.load
import com.elnemr.movieflix.databinding.RowItemMovieBinding
import com.elnemr.movieflix.domain.model.Movie
import com.elnemr.movieflix.presentation.adapter.base.BaseViewHolder

class MovieViewHolder(private val binding: RowItemMovieBinding) :
    BaseViewHolder<Movie>(binding) {

    override fun bind(result: Movie) {
        binding.ivMovie.load("https://image.tmdb.org/t/p/w154${result.posterPath}"){
            crossfade(true)
        }
        binding.title.text = result.title
        binding.voteAverage.text = result.voteAverage.toString()
        binding.originalLanguage.text = result.originalLanguage
        binding.date.text = result.releaseDate
    }
}
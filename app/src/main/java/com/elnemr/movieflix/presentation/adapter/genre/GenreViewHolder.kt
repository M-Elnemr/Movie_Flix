package com.elnemr.movieflix.presentation.adapter.genre

import com.elnemr.movieflix.databinding.RowItemGenreBinding
import com.elnemr.core.domain.model.Genre
import com.elnemr.movieflix.presentation.adapter.base.BaseViewHolder

class GenreViewHolder(private val binding: RowItemGenreBinding) :
    BaseViewHolder<Genre>(binding) {

    override fun bind(result: Genre) {
        binding.title.text = result.name
    }
}
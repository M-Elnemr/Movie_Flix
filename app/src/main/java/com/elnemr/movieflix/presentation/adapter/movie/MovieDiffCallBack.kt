package com.elnemr.movieflix.presentation.adapter.movie

import androidx.recyclerview.widget.DiffUtil
import com.elnemr.movieflix.domain.model.Movie

object MovieDiffCallBack : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }
}
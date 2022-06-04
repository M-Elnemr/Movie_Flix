package com.elnemr.movieflix.presentation.adapter.genre

import androidx.recyclerview.widget.DiffUtil
import com.elnemr.movieflix.domain.model.Genre

object GenreDiffCallBack : DiffUtil.ItemCallback<Genre>() {
    override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }
}
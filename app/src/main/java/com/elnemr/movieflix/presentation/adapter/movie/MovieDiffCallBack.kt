package com.elnemr.movieflix.presentation.adapter.movie

import androidx.recyclerview.widget.DiffUtil
import com.elnemr.movieflix.domain.model.Results

object MovieDiffCallBack : DiffUtil.ItemCallback<Results>() {
    override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }
}
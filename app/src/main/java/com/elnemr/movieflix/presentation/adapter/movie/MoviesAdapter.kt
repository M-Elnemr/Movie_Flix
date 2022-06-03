package com.elnemr.movieflix.presentation.adapter.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import com.elnemr.movieflix.databinding.RowItemMovieBinding
import com.elnemr.movieflix.domain.model.Results
import com.elnemr.movieflix.presentation.adapter.base.BasePaginationAdapter
import com.elnemr.movieflix.presentation.adapter.base.BaseViewHolder
import com.elnemr.movieflix.presentation.adapter.base.OnItemClickInterface
import com.elnemr.movieflix.presentation.util.preventDoubleClick
import kotlinx.android.synthetic.main.row_item_movie.view.*

class MoviesAdapter(private val onClickListener: OnItemClickInterface) :
    BasePaginationAdapter<Results>(MovieDiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Results> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowItemMovieBinding.inflate(inflater)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Results>, position: Int) {
        val container = holder.itemView.movie_mainLayout
        container.setOnClickListener {
            it.preventDoubleClick()
            onClickListener.onDetailsClicked(getItem(position)!!, container)
        }

        container.transitionName = "Results$position"
        holder.bind(getItem(position)!!)
    }
}
package com.elnemr.movieflix.presentation.adapter.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import com.elnemr.movieflix.databinding.RowItemMovieBinding
import com.elnemr.movieflix.domain.model.Movie
import com.elnemr.movieflix.presentation.adapter.base.BasePaginationAdapter
import com.elnemr.movieflix.presentation.adapter.base.BaseViewHolder
import com.elnemr.movieflix.presentation.util.preventDoubleClick
import kotlinx.android.synthetic.main.row_item_movie.view.*

class MoviesAdapter(private val onClickListener: OnMovieClickInterface) :
    BasePaginationAdapter<Movie>(MovieDiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Movie> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowItemMovieBinding.inflate(inflater)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Movie>, position: Int) {
        val container = holder.itemView.movie_mainLayout
        val image = holder.itemView.iv_movie
        image.transitionName = "Movie$position"
        container.setOnClickListener {
            it.preventDoubleClick()
            onClickListener.onDetailsClicked(getItem(position)!!, image)
        }

        holder.bind(getItem(position)!!)
    }
}
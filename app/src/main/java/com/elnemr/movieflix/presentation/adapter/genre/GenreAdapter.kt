package com.elnemr.movieflix.presentation.adapter.genre

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import com.elnemr.movieflix.databinding.RowItemGenreBinding
import com.elnemr.core.domain.model.Genre
import com.elnemr.movieflix.presentation.adapter.base.BaseAdapter
import com.elnemr.movieflix.presentation.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.row_item_genre.view.*

class GenreAdapter(private val onGenreClickInterface: OnGenreClickInterface) : BaseAdapter<Genre>() {

    private val mDiffer = AsyncListDiffer(this, GenreDiffCallBack)

    private var selectedPosition = 0
    override fun setDataList(dataList: List<Genre>) = mDiffer.submitList(dataList)

    override fun addDataList(dataList: List<Genre>) {
        mDiffer.currentList.addAll(dataList)
    }

    override fun clearDataList() = mDiffer.currentList.clear()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Genre> =
        GenreViewHolder(
            RowItemGenreBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: BaseViewHolder<Genre>, position: Int) {
        holder.bind(mDiffer.currentList[position])

        holder.itemView.title.setTextColor(Color.parseColor("#808080"))
        if (position == selectedPosition){
            holder.itemView.title.setTextColor(Color.GREEN)
        }

        holder.itemView.setOnClickListener {
            selectedPosition = holder.bindingAdapterPosition
            onGenreClickInterface.onGenreClicked(mDiffer.currentList[position].id)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = mDiffer.currentList.size
}
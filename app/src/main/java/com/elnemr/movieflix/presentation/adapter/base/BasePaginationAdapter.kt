package com.elnemr.movieflix.presentation.adapter.base

import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil

abstract class BasePaginationAdapter<T : Any>(diffCallback: DiffUtil.ItemCallback<T>) :
    PagingDataAdapter<T, BaseViewHolder<T>>(diffCallback)
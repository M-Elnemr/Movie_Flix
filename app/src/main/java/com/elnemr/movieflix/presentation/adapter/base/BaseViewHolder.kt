package com.elnemr.movieflix.presentation.adapter.base

import android.view.View
import androidx.viewbinding.ViewBinding
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

abstract class BaseViewHolder<in T>(private val binding: ViewBinding) :
    RecyclerView.ViewHolder(binding.root), LayoutContainer {
    protected val context = binding.root.context
    protected val resources = binding.root.resources
    override val containerView: View = binding.root

    abstract fun bind(result: T)
}
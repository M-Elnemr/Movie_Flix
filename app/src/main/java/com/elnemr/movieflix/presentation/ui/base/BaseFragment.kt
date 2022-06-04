package com.elnemr.movieflix.presentation.ui.base

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import coil.load
import com.elnemr.movieflix.presentation.util.appContext
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseFragment(layoutResourceID: Int) : Fragment(layoutResourceID) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpViewModelStateObservers()
    }

    protected open fun setUpViewModelStateObservers() {}

    protected fun initSharedTransition(
        view: ImageView,
        transitionName: String,
        fragment: Fragment,
        imagePath: String
    ) {
        view.transitionName = transitionName
        val transition =
            TransitionInflater.from(appContext()).inflateTransition(android.R.transition.move)
        fragment.sharedElementEnterTransition = transition
        fragment.sharedElementReturnTransition = transition

        view.load("https://image.tmdb.org/t/p/w154$imagePath")
    }

    protected fun initPostponeTransition(view: View, fragment: Fragment) {
        fragment.postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
    }
}
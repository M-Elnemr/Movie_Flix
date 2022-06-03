package com.elnemr.movieflix.presentation.ui

import android.os.Bundle
import com.elnemr.movieflix.R
import com.elnemr.movieflix.presentation.ui.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
package com.elnemr.movieflix.presentation.ui

import android.os.Bundle
import com.elnemr.movieflix.R
import com.elnemr.movieflix.databinding.ActivityMainBinding
import com.elnemr.movieflix.presentation.ui.base.BaseActivity

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
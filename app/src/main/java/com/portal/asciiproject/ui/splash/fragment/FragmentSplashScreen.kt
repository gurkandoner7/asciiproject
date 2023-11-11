package com.portal.asciiproject.ui.splash.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.viewModels
import com.deportak.compose.BaseFragment
import com.deportak.compose.viewBinding
import com.portal.asciiproject.R
import com.portal.asciiproject.databinding.FragmentSplashScreenBinding
import com.portal.asciiproject.ui.splash.viewmodel.SplashScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class FragmentSplashScreen : BaseFragment(R.layout.fragment_splash_screen) {

    private val splashViewModel: SplashScreenViewModel by viewModels()

    private val binding: FragmentSplashScreenBinding by viewBinding(FragmentSplashScreenBinding::bind)


    override fun observeVariables() {
    }


    override fun initUI(savedInstanceState: Bundle?) {
    }


}
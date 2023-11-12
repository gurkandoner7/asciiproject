package com.portal.asciiproject.ui.home.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.viewModels
import com.portal.asciiproject.compose.BaseFragment
import com.portal.asciiproject.compose.viewBinding
import com.portal.asciiproject.R
import com.portal.asciiproject.databinding.FragmentSplashScreenBinding
import com.portal.asciiproject.ui.splash.viewmodel.SplashScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class FragmentHome : BaseFragment(R.layout.fragment_splash_screen) {

    private val splashViewModel: SplashScreenViewModel by viewModels()

    private val binding: FragmentSplashScreenBinding by viewBinding(FragmentSplashScreenBinding::bind)


    override fun observeVariables() {
    }


    override fun initUI(savedInstanceState: Bundle?) {
    }


}
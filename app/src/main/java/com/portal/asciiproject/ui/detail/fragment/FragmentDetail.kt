package com.portal.asciiproject.ui.detail.fragment

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.portal.asciiproject.R
import com.portal.asciiproject.compose.BaseFragment
import com.portal.asciiproject.compose.viewBinding
import com.portal.asciiproject.databinding.FragmentDetailBinding
import com.portal.asciiproject.ui.detail.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentDetail : BaseFragment(R.layout.fragment_detail) {
    private val detailViewModel: DetailViewModel by viewModels()

    private val binding: FragmentDetailBinding by viewBinding(FragmentDetailBinding::bind)
    override fun observeVariables() {
    }

    override fun initUI(savedInstanceState: Bundle?) {
    }

}
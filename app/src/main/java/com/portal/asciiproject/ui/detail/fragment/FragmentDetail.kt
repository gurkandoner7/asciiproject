package com.portal.asciiproject.ui.detail.fragment

import android.os.Bundle
import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.portal.asciiproject.R
import com.portal.asciiproject.compose.BaseFragment
import com.portal.asciiproject.compose.viewBinding
import com.portal.asciiproject.databinding.FragmentDetailBinding
import com.portal.asciiproject.ui.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentDetail : BaseFragment(R.layout.fragment_detail) {
    private val homeViewModel: HomeViewModel by activityViewModels()

    private val binding: FragmentDetailBinding by viewBinding(FragmentDetailBinding::bind)


    override fun observeVariables() {
        lifecycleScope.launchWhenStarted {
            homeViewModel.selectedItem.collect { productItem ->
                productItem?.let {
                    binding.tvWith.setText(it.productWith)
                    binding.tvPrice.setText(it.productPrice)
                    binding.tvDesc.setText(it.productDesc)
                    binding.tvProductName.setText(it.productName)
                    it.productImage?.let { imageResId ->
                        binding.ivImage.setImageResource(imageResId)
                    }
                }
            }
        }    }

    override fun initUI(savedInstanceState: Bundle?) {
        requireActivity().onBackPressedDispatcher.addCallback(this) { findNavController().navigate(R.id.action_detailFragment_to_homeFragment) }
        binding.btnBack.setOnClickListener {
           findNavController().navigate(R.id.action_detailFragment_to_homeFragment)
        }
    }

}
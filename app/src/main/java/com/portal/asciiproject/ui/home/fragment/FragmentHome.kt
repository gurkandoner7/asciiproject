package com.portal.asciiproject.ui.home.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.portal.asciiproject.R
import com.portal.asciiproject.compose.BaseFragment
import com.portal.asciiproject.compose.viewBinding
import com.portal.asciiproject.databinding.FragmentHomeBinding
import com.portal.asciiproject.ui.home.adapter.CoffeeTypesAdapter
import com.portal.asciiproject.ui.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class FragmentHome : BaseFragment(R.layout.fragment_home) {

    private val homeViewModel: HomeViewModel by viewModels()

    private val binding: FragmentHomeBinding by viewBinding(FragmentHomeBinding::bind)

    private lateinit var adapter: CoffeeTypesAdapter


    override fun observeVariables() {
        lifecycleScope.launchWhenStarted {
            launch {
                homeViewModel.productList.collect {
                    adapter.addItem(it)
                }
            }
        }
    }


    override fun initUI(savedInstanceState: Bundle?) {
        adapter = CoffeeTypesAdapter(requireContext(), mutableListOf(),
            onItemClicked = {
                findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
            })
        binding.rvTabs.adapter = adapter
        requireActivity().onBackPressedDispatcher.addCallback(this) {}
        binding.tvLocation.setText("Atasehir, Istanbul")
    }


}
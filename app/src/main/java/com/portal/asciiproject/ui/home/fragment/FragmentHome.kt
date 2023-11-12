package com.portal.asciiproject.ui.home.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.portal.asciiproject.compose.BaseFragment
import com.portal.asciiproject.compose.viewBinding
import com.portal.asciiproject.R
import com.portal.asciiproject.databinding.FragmentHomeBinding
import com.portal.asciiproject.databinding.FragmentSplashScreenBinding
import com.portal.asciiproject.ui.home.adapter.CoffeeTypesAdapter
import com.portal.asciiproject.ui.home.viewmodel.HomeViewModel
import com.portal.asciiproject.ui.splash.viewmodel.SplashScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class FragmentHome : BaseFragment(R.layout.fragment_home) {

    private val homeViewModel: HomeViewModel by viewModels()

    private val binding: FragmentHomeBinding by viewBinding(FragmentHomeBinding::bind)

    private lateinit var adapter: CoffeeTypesAdapter


    override fun observeVariables() {
        adapter = CoffeeTypesAdapter(requireContext(), mutableListOf()) // Adapter'i başlat
        binding.rvTabs.adapter = adapter // RecyclerView'a adapteri atayın
        lifecycleScope.launchWhenStarted {
            launch {
                homeViewModel.productList.collect{
                adapter.addItem(it)
                }
            }

        }
    }


    override fun initUI(savedInstanceState: Bundle?) {
        requireActivity().onBackPressedDispatcher.addCallback(this) {}
        binding.tvLocation.setText("Atasehir, Istanbul")
    }


}
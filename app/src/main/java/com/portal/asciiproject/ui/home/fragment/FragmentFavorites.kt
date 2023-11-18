package com.portal.asciiproject.ui.home.fragment

import FavoritesAdapter
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.portal.asciiproject.R
import com.portal.asciiproject.compose.BaseFragment
import com.portal.asciiproject.compose.viewBinding
import com.portal.asciiproject.databinding.FragmentFavoritesBinding
import com.portal.asciiproject.databinding.FragmentHomeBinding
import com.portal.asciiproject.ui.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentFavorites : BaseFragment(R.layout.fragment_favorites){
    private val favViewModel: HomeViewModel by activityViewModels()
    private val binding: FragmentFavoritesBinding by viewBinding(FragmentFavoritesBinding::bind)
    private lateinit var adapter: FavoritesAdapter

    override fun observeVariables() {

    }

    override fun initUI(savedInstanceState: Bundle?) {
        binding.homeNavView.selectedItemId = R.id.navigation_favorites
        binding.homeNavView.apply {
            val navController = findNavController()
            setupWithNavController(navController)
            setOnNavigationItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.navigation_list -> {
                        navController.navigate(R.id.homeFragment)
                        true
                    }

                    R.id.navigation_favorites -> {
                        navController.navigate(R.id.fragmentFavorites)
                        true
                    }

                    else -> false
                }
            }
            adapter = FavoritesAdapter(requireContext(),favViewModel.favoriteItems.value)
            binding.rvTabs.adapter = adapter
        }
    }

}
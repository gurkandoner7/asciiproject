package com.portal.asciiproject.ui.home.fragment

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
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

    private val homeViewModel: HomeViewModel by activityViewModels()

    private val binding: FragmentHomeBinding by viewBinding(FragmentHomeBinding::bind)

    private lateinit var adapter: CoffeeTypesAdapter

    private val bundle = Bundle()


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
        }

        adapter = CoffeeTypesAdapter(requireContext(), mutableListOf(),
            onItemClicked = {
                homeViewModel.setSelectedItem(it)
                findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
            })
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigate(R.id.action_homeFragment_to_splashFragment)

        }

        binding.apply {
            rvTabs.adapter = adapter
            tvLocation.setText("Atasehir, Istanbul")

            radioGroup.setOnCheckedChangeListener { _, checkedId ->
                setRadioGroupColors(checkedId)
                when (checkedId) {
                    radioButton1.id -> adapter.filter("Cappuccino")
                    radioButton2.id -> adapter.filter("Mocha")
                    radioButton3.id -> adapter.filter("Americano")
                    radioButton4.id -> adapter.filter("Latte")
                }
            }
            svCoffee.apply {
                setOnQueryTextChangedListener { newText ->
                    radioGroup.clearCheck()
                    if (newText != "") {
                        adapter.filter(newText)
                        adapter.getFilteredItems()
                    } else {
                        adapter.clearFilter()
                    }
                }
                setOnSearchClosedListener {
                    adapter.clearFilter()
                }
            }
        }

    }


    private fun setRadioGroupColors(checkedId: Int) {
        binding.apply {
            radioButton1.setTextColor(if (checkedId == radioButton1.id) Color.WHITE else Color.BLACK)
            radioButton2.setTextColor(if (checkedId == radioButton2.id) Color.WHITE else Color.BLACK)
            radioButton3.setTextColor(if (checkedId == radioButton3.id) Color.WHITE else Color.BLACK)
            radioButton4.setTextColor(if (checkedId == radioButton4.id) Color.WHITE else Color.BLACK)
        }
    }

}



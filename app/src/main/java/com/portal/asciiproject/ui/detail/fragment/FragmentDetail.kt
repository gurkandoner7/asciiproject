package com.portal.asciiproject.ui.detail.fragment

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.portal.asciiproject.CompletedDialogFragment
import com.portal.asciiproject.R
import com.portal.asciiproject.compose.BaseFragment
import com.portal.asciiproject.compose.viewBinding
import com.portal.asciiproject.databinding.FragmentDetailBinding
import com.portal.asciiproject.ui.home.viewmodel.HomeViewModel
import com.portal.asciiproject.utilities.helper.Util.Companion.MAGIC_KEY
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
                    binding.tvPrice.setText(it.productUnit + it.productPrice.toString())
                    binding.tvDesc.setText(it.productDesc)
                    binding.tvProductName.setText(it.productName)
                    it.productImage?.let { imageResId ->
                        binding.ivImage.setImageResource(imageResId)
                    }
                }
            }
        }
    }

    override fun initUI(savedInstanceState: Bundle?) {
        requireActivity().onBackPressedDispatcher.addCallback(this) { findNavController().navigate(R.id.action_detailFragment_to_homeFragment) }
        binding.apply {
            radioButton2.isChecked = true
            radioButton2.setTextColor(Color.WHITE)
            btnBack.setOnClickListener {
                findNavController().navigate(R.id.action_detailFragment_to_homeFragment)
            }
            radioGroup.setOnCheckedChangeListener { _, checkedId ->
                setRadioGroupColors(checkedId)
                when (checkedId) {
                    radioButton1.id -> homeViewModel.setCheckedRadio("S")
                    radioButton2.id -> homeViewModel.setCheckedRadio("M")
                    radioButton3.id -> homeViewModel.setCheckedRadio("L")
                }
            }
            btnBuyNow.setButtonOnClick {
                showConfirmationDialog()
            }
        }
    }

    private fun setRadioGroupColors(checkedId: Int) {
        binding.apply {
            radioButton1.setTextColor(if (checkedId == radioButton1.id) Color.WHITE else Color.BLACK)
            radioButton2.setTextColor(if (checkedId == radioButton2.id) Color.WHITE else Color.BLACK)
            radioButton3.setTextColor(if (checkedId == radioButton3.id) Color.WHITE else Color.BLACK)
        }
    }


    private fun showConfirmationDialog() {
        val builder = AlertDialog.Builder(requireContext(), R.style.AlertDialogCustom)
        builder.setTitle(context?.getString(R.string.confirmation))
            .setMessage(homeViewModel.checkedRadio.value?.let {
                context?.getString(R.string.buy_now_dialog)?.replace(
                    MAGIC_KEY,
                    it
                )
            })
            .setPositiveButton(context?.getString(R.string.approve)) { dialog, _ ->
                dialog.dismiss()
                val completedDialogFragment = CompletedDialogFragment()
                completedDialogFragment.show(
                    parentFragmentManager,
                    FragmentDetail::class.java.simpleName
                )

            }
            .setNegativeButton(context?.getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }
}
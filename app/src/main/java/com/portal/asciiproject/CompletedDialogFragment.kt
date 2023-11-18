package com.portal.asciiproject

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.portal.asciiproject.databinding.FragmentCompletedDialogBinding
import com.bumptech.glide.Glide
class CompletedDialogFragment : DialogFragment() {

    private var _binding: FragmentCompletedDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCompletedDialogBinding.inflate(inflater, container, false)
        dialog?.let {
            it.window?.requestFeature(Window.FEATURE_NO_TITLE)
            it.window?.setBackgroundDrawable(ColorDrawable(Color.WHITE))
            it.setCancelable(false)
        }
        loadGif()
        setButtonClickListeners()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onStart() {
        super.onStart()
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.MATCH_PARENT
        dialog?.window?.setLayout(width, height)
    }
    private fun loadGif() {
        Glide.with(this)
            .asGif()
            .load(R.drawable.coffeegif)
            .into(binding.ivCoffee)
    }
    private fun setButtonClickListeners() {
        binding.btnGoHome.setButtonOnClick {
            findNavController().navigate(R.id.action_detailFragment_to_homeFragment)
            dismiss()
        }
    }
}
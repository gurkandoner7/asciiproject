package com.portal.asciiproject.ui.splash.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.portal.asciiproject.R
import com.portal.asciiproject.compose.BaseFragment
import com.portal.asciiproject.compose.viewBinding
import com.portal.asciiproject.databinding.FragmentSplashScreenBinding
import com.portal.asciiproject.ui.splash.viewmodel.SplashScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class FragmentSplashScreen : BaseFragment(R.layout.fragment_splash_screen) {

    private val splashViewModel: SplashScreenViewModel by viewModels()

    private val binding: FragmentSplashScreenBinding by viewBinding(FragmentSplashScreenBinding::bind)


    override fun observeVariables() {
    }


    override fun initUI(savedInstanceState: Bundle?) {
        binding.apply {
            setLocale(getLanguageFromSharedPreferences())
            when (getLanguageFromSharedPreferences()) {
                TURKISH -> {
                    radioTurkish.isChecked = true
                }

                ENGLISH -> {
                    radioEnglish.isChecked = true
                }

                else -> setLocale(Locale.getDefault().language)
            }

            radioGroup.setOnCheckedChangeListener { group, checkedId ->
                when (checkedId) {
                    radioEnglish.id -> {
                        saveLanguageToSharedPreferences(ENGLISH)
                        setLocale(getLanguageFromSharedPreferences())
                        requireActivity().recreate()

                    }

                    radioTurkish.id -> {
                        saveLanguageToSharedPreferences(TURKISH)
                        setLocale(getLanguageFromSharedPreferences())
                        requireActivity().recreate()

                    }
                }
            }

        }
        binding.btnSplash.setButtonOnClick {
            findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
        }
    }

    private fun setLocale(language: String) {

        val locale = Locale(language)
        Locale.setDefault(locale)

        val resources = requireContext().resources
        val configuration = Configuration(resources.configuration)
        configuration.setLocale(locale)
        resources.updateConfiguration(configuration, resources.displayMetrics)
        requireActivity()
    }

    private fun saveLanguageToSharedPreferences(language: String) {
        val sharedPreferences: SharedPreferences =
            requireContext().getSharedPreferences(LANGUAGE_KEY, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(LANGUAGE_KEY, language)
        editor.apply()
    }

    private fun getLanguageFromSharedPreferences(): String {
        val sharedPreferences: SharedPreferences =
            requireContext().getSharedPreferences(LANGUAGE_KEY, Context.MODE_PRIVATE)
        return sharedPreferences.getString(LANGUAGE_KEY, ENGLISH) ?: TURKISH
    }

    companion object {
        const val TURKISH = "tr"
        const val ENGLISH = "en"
        const val LANGUAGE_KEY = "language"
    }
}
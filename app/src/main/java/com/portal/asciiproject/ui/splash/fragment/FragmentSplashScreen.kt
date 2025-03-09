package com.portal.asciiproject.ui.splash.fragment

import SplashScreen
import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import androidx.compose.ui.platform.ComposeView
import androidx.core.content.edit
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.portal.asciiproject.R
import com.portal.asciiproject.compose.BaseFragment
import com.portal.asciiproject.ui.splash.viewmodel.SplashScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class FragmentSplashScreen : BaseFragment(R.layout.fragment_splash_screen) {

    private val splashViewModel: SplashScreenViewModel by viewModels()

    override fun observeVariables() {
    }

    override fun setupComposeUI(composeView: ComposeView) {
        composeView.setContent {
            SplashScreen(
                viewModel = splashViewModel,
                onLanguageSelected = { language ->
                    saveLanguageToSharedPreferences(language)
                    setLocale(language)
                    requireActivity().recreate()
                },
                onGetStartedClicked = {
                    findNavController().navigate(R.id.action_splashFragment_to_evkurTestFragment)
                }
            )
        }
    }

    private fun setLocale(language: String) {
        val locale = Locale(language.lowercase())
        Locale.setDefault(locale)
        val resources = requireContext().resources
        val configuration = Configuration(resources.configuration)
        configuration.setLocale(locale)
        resources.updateConfiguration(configuration, resources.displayMetrics)
    }

    private fun saveLanguageToSharedPreferences(language: String) {
        val sharedPreferences: SharedPreferences =
            requireContext().getSharedPreferences(LANGUAGE_KEY, Context.MODE_PRIVATE)
        sharedPreferences.edit {
            putString(LANGUAGE_KEY, language.lowercase())
        }
    }

    companion object {
        const val LANGUAGE_KEY = "language"
    }
}

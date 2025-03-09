package com.portal.asciiproject.ui.splash.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor() : ViewModel() {

    fun getLanguageFromSharedPreferences(context: Context): String {
        val prefs = context.getSharedPreferences(LANGUAGE_KEY, Context.MODE_PRIVATE)
        return prefs.getString(LANGUAGE_KEY, ENGLISH)?.lowercase() ?: ENGLISH
    }
    companion object {
        const val TURKISH = "tr"
        const val ENGLISH = "en"
        const val LANGUAGE_KEY = "language"
    }
}
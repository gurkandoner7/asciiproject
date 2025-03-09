package com.portal.asciiproject.ui.evkur

import ParameterTabs

import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import com.portal.asciiproject.R
import com.portal.asciiproject.compose.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EvkurTestFragment : BaseFragment(R.layout.fragment_evkur_test) {

    private val evkurTestViewModel: EvkurTestViewModel by viewModels()

    override fun observeVariables() {
    }

    override fun setupComposeUI(composeView: ComposeView) {
        composeView.setContent {
            val parameters = List(6) { tabIndex ->
                List(5) { paramIndex ->
                    "Parameter ${tabIndex + 1} - ${paramIndex + 1}" to List(30) { "Content ${tabIndex + 1} - ${paramIndex + 1} - Detail ${it + 1}" }
                }
            }
            ParameterTabs(parameters = parameters)
        }
    }
}
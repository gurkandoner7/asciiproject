package com.portal.asciiproject.compose

import android.os.Bundle
import android.view.View
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.portal.asciiproject.R

abstract class BaseFragment(bindingLayout: Int) : Fragment(bindingLayout) {

    open fun initUI(savedInstanceState: Bundle?) {}

    open fun setupComposeUI(composeView: ComposeView) {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeVariables()
        initUI(savedInstanceState)
        view.findViewById<ComposeView>(R.id.compose_view)?.let {
            setupComposeUI(it)
        }
    }

    abstract fun observeVariables()
}
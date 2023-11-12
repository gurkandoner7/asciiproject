package com.portal.asciiproject.utilities.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import com.portal.asciiproject.databinding.CustomSearchViewBinding

class CustomSearchView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var onQueryTextChangedListener: ((String) -> Unit)? = null
    private var onSearchSubmittedListener: ((String) -> Unit)? = null
    private var onSearchClosedListener: (() -> Unit)? = null
    private var _binding: CustomSearchViewBinding? = null
    private val binding: CustomSearchViewBinding
        get() = _binding!!

    init {
        _binding = CustomSearchViewBinding.inflate(LayoutInflater.from(context), this, true)
        binding.deportakFilterSearch.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(query: String?): Boolean {
                    onSearchSubmittedListener?.invoke(query ?: "")

                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    onQueryTextChangedListener?.invoke(newText ?: "")
                    if (newText?.isEmpty() == true) {
                        onSearchClosedListener?.invoke()
                    }
                    return false
                }
            }
        )

        binding.deportakFilterSearch.setOnCloseListener {
            onSearchClosedListener?.invoke()
            true
        }

    }

    fun setOnQueryTextChangedListener(listener: (String) -> Unit) {
        onQueryTextChangedListener = listener
    }

    fun setOnSearchSubmittedListener(listener: (String) -> Unit) {
        onSearchSubmittedListener = listener
    }

    fun setOnSearchClosedListener(listener: () -> Unit) {
        onSearchClosedListener = listener
    }

}

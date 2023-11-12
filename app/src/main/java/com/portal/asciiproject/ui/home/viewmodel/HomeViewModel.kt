package com.portal.asciiproject.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import com.portal.asciiproject.R
import com.portal.asciiproject.data.ProductItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val _productList = MutableStateFlow<List<ProductItem>>(emptyList())
    val productList = _productList.asStateFlow()

    init {
        val sampleProducts = listOf(
            ProductItem("Coffee", "Cappuccino", "Oat Milk", "$2.99", "per cup", R.drawable.coffee3),
            ProductItem("Coffee", "Espresso", "Chocolate", "$2.49", "per cup", R.drawable.coffee4),
            ProductItem("Coffee", "Mocha", "Oat Milk", "$3.49", "per cup", R.drawable.coffee5),
            ProductItem("Coffee", "Latte", "Chocolate", "$3.99", "per cup", R.drawable.coffee2),
            ProductItem("Coffee", "Macchiato", "Oat Milk", "$3.29", "per cup", R.drawable.coffee1),
            ProductItem("Coffee", "Americano", "Chocolate", "$2.99", "per cup", R.drawable.coffee4),
            ProductItem("Coffee", "Flat White", "Oat Milk", "$3.49", "per cup", R.drawable.coffee3),
            ProductItem("Coffee", "Iced Coffee", "Chocolate", "$3.99", "per cup", R.drawable.coffee5)

        )
        setProductListItems(sampleProducts)
    }

    private fun setProductListItems(productItems: List<ProductItem>) {
        _productList.value = productItems
    }
}
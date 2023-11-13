package com.portal.asciiproject.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import com.portal.asciiproject.R
import com.portal.asciiproject.data.ProductItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val _productList = MutableStateFlow<List<ProductItem>>(emptyList())
    val productList = _productList.asStateFlow()

    private val _selectedItem = MutableStateFlow<ProductItem?>(null)
    val selectedItem = _selectedItem.asStateFlow()

    init {
        val sampleProducts = listOf(
            ProductItem("Coffee", "Cappuccino", "Oat Milk", "$2.99", "per cup", R.drawable.coffee5),
            ProductItem("Coffee", "Espresso", "Chocolate", "$2.49", "per cup", R.drawable.coffee3),
            ProductItem("Coffee", "Mocha", "Almond Milk", "$3.49", "per cup", R.drawable.coffee2),
            ProductItem("Coffee", "Latte", "Hazelnut Milk", "$3.99", "per cup", R.drawable.coffee1),
            ProductItem("Coffee", "Macchiato", "Coconut Milk", "$3.29", "per cup", R.drawable.coffee4),
            ProductItem("Coffee", "Americano", "Cashew Milk", "$2.99", "per cup", R.drawable.coffee5),
            ProductItem("Coffee", "Flat White", "Soy Milk", "$3.49", "per cup", R.drawable.coffee2),
            ProductItem("Coffee", "Espresso", "Oat Milk", "$2.49", "per cup", R.drawable.coffee3),
            ProductItem("Coffee", "Latte", "Almond Milk", "$3.99", "per cup", R.drawable.coffee1),
            ProductItem("Coffee", "Macchiato", "Hazelnut Milk", "$3.29", "per cup", R.drawable.coffee4),
            ProductItem("Coffee", "Americano", "Coconut Milk", "$2.99", "per cup", R.drawable.coffee5),
            ProductItem("Coffee", "Flat White", "Cashew Milk", "$3.49", "per cup", R.drawable.coffee2),
            ProductItem("Coffee", "Espresso", "Soy Milk", "$2.49", "per cup", R.drawable.coffee3),
            ProductItem("Coffee", "Americano", "Oat Milk", "$2.99", "per cup", R.drawable.coffee4),
            ProductItem("Coffee", "Flat White", "Almond Milk", "$3.49", "per cup", R.drawable.coffee5),
            ProductItem("Coffee", "Espresso", "Hazelnut Milk", "$2.49", "per cup", R.drawable.coffee1),
            ProductItem("Coffee", "Latte", "Coconut Milk", "$3.99", "per cup", R.drawable.coffee2),
            ProductItem("Coffee", "Macchiato", "Cashew Milk", "$3.29", "per cup", R.drawable.coffee3),
            ProductItem("Coffee", "Americano", "Soy Milk", "$2.99", "per cup", R.drawable.coffee4),
            ProductItem("Coffee", "Flat White", "Oat Milk", "$3.49", "per cup", R.drawable.coffee5),
            ProductItem("Coffee", "Espresso", "Almond Milk", "$2.49", "per cup", R.drawable.coffee2),
            ProductItem("Coffee", "Latte", "Hazelnut Milk", "$3.99", "per cup", R.drawable.coffee1),
            ProductItem("Coffee", "Macchiato", "Coconut Milk", "$3.29", "per cup", R.drawable.coffee4),
            ProductItem("Coffee", "Americano", "Cashew Milk", "$2.99", "per cup", R.drawable.coffee5),
            ProductItem("Coffee", "Flat White", "Soy Milk", "$3.49", "per cup", R.drawable.coffee2),
            ProductItem("Coffee", "Espresso", "Oat Milk", "$2.49", "per cup", R.drawable.coffee3),
            ProductItem("Coffee", "Latte", "Almond Milk", "$3.99", "per cup", R.drawable.coffee1),
            ProductItem("Coffee", "Macchiato", "Hazelnut Milk", "$3.29", "per cup", R.drawable.coffee4),
            ProductItem("Coffee", "Americano", "Coconut Milk", "$2.99", "per cup", R.drawable.coffee5),
            ProductItem("Coffee", "Flat White", "Cashew Milk", "$3.49", "per cup", R.drawable.coffee2),
            ProductItem("Coffee", "Espresso", "Soy Milk", "$2.49", "per cup", R.drawable.coffee3),
            ProductItem("Coffee", "Latte", "Oat Milk", "$3.99", "per cup", R.drawable.coffee4),
        )
        setProductListItems(sampleProducts)
    }
    fun setSelectedItem(item: ProductItem?) {
        _selectedItem.value = item
    }

    private fun setProductListItems(productItems: List<ProductItem>) {
        _productList.value = productItems
    }
}
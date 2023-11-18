package com.portal.asciiproject.ui.home.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.portal.asciiproject.R
import com.portal.asciiproject.data.ProductItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(context: Context) : ViewModel() {
    private val _productList = MutableStateFlow<List<ProductItem>>(emptyList())
    val productList = _productList.asStateFlow()

    private val _selectedItem = MutableStateFlow<ProductItem?>(null)
    val selectedItem = _selectedItem.asStateFlow()

    private val _checkedRadio = MutableStateFlow<String?>("M")
    val checkedRadio = _checkedRadio.asStateFlow()

    private val _favoriteItems = MutableStateFlow<MutableList<ProductItem?>?>(mutableListOf())
    val favoriteItems = _favoriteItems.asStateFlow()


    init {
        val sampleProducts = listOf(
            ProductItem(

                "Cappuccino", context.getString(R.string.product_milk_oat), "$2.99",

                R.drawable.coffee5, productFavorite = true
            ),
            ProductItem(

                "Espresso", context.getString(R.string.product_milk_chocolate), "$2.49",

                R.drawable.coffee1, productFavorite = true
            ),
            ProductItem(

                "Mocha", context.getString(R.string.product_milk_almond), "$3.49",

                R.drawable.coffee2, productFavorite = true
            ),
            ProductItem(

                "Cappuccino", context.getString(R.string.product_milk_oat), "$2.99",

                R.drawable.coffee10, productFavorite = true
            ),
            ProductItem("Latte", context.getString(R.string.product_milk_hazelnut), "$3.99", R.drawable.coffee1),
            ProductItem(
                "Macchiato", context.getString(R.string.product_milk_coconut), "$3.29", R.drawable.coffee4
            ),
            ProductItem("Cappuccino", context.getString(R.string.product_milk_oat), "$2.99", R.drawable.coffee3),
            ProductItem(
                "Americano", context.getString(R.string.product_milk_cashew), "$2.99", R.drawable.coffee1
            ),
            ProductItem("Flat White", context.getString(R.string.product_milk_soy), "$3.49", R.drawable.coffee2),
            ProductItem("Espresso", context.getString(R.string.product_milk_oat), "$2.49", R.drawable.coffee3),
            ProductItem("Latte", context.getString(R.string.product_milk_almond), "$3.99", R.drawable.coffee1),
            ProductItem(
                "Macchiato", context.getString(R.string.product_milk_hazelnut), "$3.29", R.drawable.coffee4
            ),
            ProductItem(
                "Americano", context.getString(R.string.product_milk_coconut), "$2.99", R.drawable.coffee5
            ),
            ProductItem(
                "Flat White", context.getString(R.string.product_milk_cashew), "$3.49", R.drawable.coffee2
            ),
            ProductItem("Espresso", context.getString(R.string.product_milk_soy), "$2.49", R.drawable.coffee5),
            ProductItem("Americano", context.getString(R.string.product_milk_oat), "$2.99", R.drawable.coffee4),
            ProductItem(
                "Flat White", context.getString(R.string.product_milk_almond), "$3.49", R.drawable.coffee5
            ),
            ProductItem(
                "Espresso", context.getString(R.string.product_milk_hazelnut), "$2.49", R.drawable.coffee9
            ),
            ProductItem("Latte", context.getString(R.string.product_milk_coconut), "$3.99", R.drawable.coffee2),
            ProductItem(
                "Macchiato", context.getString(R.string.product_milk_cashew), "$3.29", R.drawable.coffee3
            ),
            ProductItem(

                "Americano", context.getString(R.string.product_milk_soy), "$2.99",

                R.drawable.coffee4, productFavorite = true
            ),
            ProductItem("Flat White", context.getString(R.string.product_milk_oat), "$3.49", R.drawable.coffee5),
            ProductItem(

                "Espresso", context.getString(R.string.product_milk_almond), "$2.49",

                R.drawable.coffee3, productFavorite = true
            ),
            ProductItem(

                "Latte", context.getString(R.string.product_milk_hazelnut), "$3.99",

                R.drawable.coffee1, productFavorite = true
            ),
            ProductItem(

                "Macchiato", context.getString(R.string.product_milk_coconut), "$3.29",

                R.drawable.coffee4, productFavorite = true
            ),
            ProductItem(

                "Americano", context.getString(R.string.product_milk_cashew), "$2.99",

                R.drawable.coffee8, productFavorite = true
            ),
            ProductItem("Flat White", context.getString(R.string.product_milk_soy), "$3.49", R.drawable.coffee2),
            ProductItem(

                "Espresso", context.getString(R.string.product_milk_oat), "$2.49",

                R.drawable.coffee2, productFavorite = true
            ),
            ProductItem("Latte", context.getString(R.string.product_milk_almond), "$3.99", R.drawable.coffee1),
            ProductItem(
                "Macchiato", context.getString(R.string.product_milk_hazelnut), "$3.29", R.drawable.coffee4
            ),
            ProductItem(

                "Americano", context.getString(R.string.product_milk_coconut), "$2.99",

                R.drawable.coffee9, productFavorite = true
            ),
            ProductItem(

                "Flat White", context.getString(R.string.product_milk_cashew), "$3.49",

                R.drawable.coffee2, productFavorite = true
            ),
            ProductItem("Espresso", context.getString(R.string.product_milk_soy), "$2.49", R.drawable.coffee7),
            ProductItem(

                "Latte", context.getString(R.string.product_milk_oat), "$3.99",

                R.drawable.coffee4, productFavorite = true
            ),
        )
        setProductListItems(sampleProducts)
    }

    fun setSelectedItem(item: ProductItem?) {
        _selectedItem.value = item
    }

    private fun setProductListItems(productItems: List<ProductItem>) {
        _productList.value = productItems
        val favoriteItems = productItems.filter { it.productFavorite }
        _favoriteItems.value?.addAll(favoriteItems)
    }

    fun setCheckedRadio(state: String) {
        _checkedRadio.value = state
    }

}
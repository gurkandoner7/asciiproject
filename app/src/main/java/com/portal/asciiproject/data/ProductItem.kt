package com.portal.asciiproject.data


data class ProductItem(
    val productName: String? = null,
    val productWith: String? = null,
    val productPrice: String? = null,
    val productImage: Int? = null,
    val productDesc: String? = null,
    var productFavorite: Boolean = false
)
package com.portal.asciiproject.data


data class ProductItem(
    val productName: String? = null,
    val productWith: String? = null,
    var productPrice: Double? = null,
    val productImage: Int? = null,
    val productDesc: String? = null,
    var productFavorite: Boolean = false,
    val productUnit: String? = null
)
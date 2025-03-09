/*
package com.portal.asciiproject.utilities.customviews

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun CustomSearchView(
    onQueryTextChanged: (String) -> Unit,
    onSearchSubmitted: (String) -> Unit,
    onSearchClosed: () -> Unit
) {
    val query = remember { mutableStateOf("") }

    TextField(
        value = query.value,
        onValueChange = {
            query.value = it
            onQueryTextChanged(it)
            if (it.isEmpty()) {
                onSearchClosed()
            } else {
                onSearchSubmitted(it)
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}*/

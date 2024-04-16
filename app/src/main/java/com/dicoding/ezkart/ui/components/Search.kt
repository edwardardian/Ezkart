package com.dicoding.ezkart.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dicoding.ezkart.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(
    modifier: Modifier = Modifier,
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit
) {
    SearchBar(
        query = searchQuery, // Menggunakan searchQuery yang diterima sebagai parameter
        onQueryChange = {
            onSearchQueryChanged(it)
        },
        onSearch = {},
        active = false,
        onActiveChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        placeholder = {
            Text(stringResource(R.string.placeholder_text))
        },
        shape = MaterialTheme.shapes.medium,
        colors = SearchBarDefaults.colors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 48.dp)
    ) {

    }
}

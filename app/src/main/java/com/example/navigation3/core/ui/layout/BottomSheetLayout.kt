package com.example.navigation3.core.ui.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetLayout(
    title: String,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
    content: @Composable () -> Unit,
) {
    val topAppBarState = rememberTopAppBarState()
    val topAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(state = topAppBarState)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(title) },
                navigationIcon = navigationIcon,
                actions = {
                    IconButton(
                        onClick = onDismissRequest,
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null,
                        )
                    }
                },
                scrollBehavior = topAppBarScrollBehavior,
            )
        },
        modifier = modifier
            .nestedScroll(topAppBarScrollBehavior.nestedScrollConnection)
            .fillMaxSize(),
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding),
        ) {
            content()
        }
    }
}

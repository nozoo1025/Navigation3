package com.example.navigation3.screen.licence

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.example.navigation3.core.ui.ext.defaultHorizontalPadding
import com.example.navigation3.core.ui.layout.BottomSheetLayout
import com.example.navigation3.screen.nav.removeLastOrKeepIfSingle

@Composable
fun LicenceBottomScreen(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val backStack = remember { mutableStateListOf<LicenceRoute>(LicenceRoute.Root) }

    BottomSheetLayout(
        title = "Licences",
        onDismissRequest = onDismissRequest,
        navigationIcon = {
            if (backStack.size > 1) {
                IconButton(
                    onClick = {
                        backStack.removeLastOrKeepIfSingle(onSingle = onDismissRequest)
                    }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                    )
                }
            }
        },
        modifier = modifier,
    ) {
        NavDisplay(
            backStack = backStack,
            onBack = { backStack.removeLastOrKeepIfSingle(onSingle = onDismissRequest) },
            entryProvider = entryProvider {
                entry<LicenceRoute.Root> {
                    LicenceRoute(
                        onNavigateToDetails = {
                            backStack.add(LicenceRoute.Details)
                        },
                    )
                }

                entry<LicenceRoute.Details> {
                    LicenceDetails(Modifier.defaultHorizontalPadding())
                }
            }
        )
    }
}

@Composable
fun LicenceRoute(
    onNavigateToDetails: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        ListItem(
            headlineContent = { Text("Koin(2.0.0)") },
            supportingContent = { Text("Arnaud Giuliani") },
            trailingContent = {
                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = null,
                )
            },
            modifier = Modifier.clickable { onNavigateToDetails() },
        )
    }
}

sealed class LicenceRoute {
    data object Root : LicenceRoute()
    data object Details : LicenceRoute()
}

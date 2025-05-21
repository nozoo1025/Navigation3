package com.example.navigation3.screen.nav

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.example.navigation3.screen.details.DetailsScreen
import com.example.navigation3.screen.home.HomeScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationScreen(modifier: Modifier = Modifier) {
    val backStack = remember { mutableStateListOf<NavRoute>(NavRoute.Home) }

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
    )
    val modalScreenController = rememberModalScreenController(sheetState)

    CompositionLocalProvider(
        LocalModalScreenController provides modalScreenController,
    ) {
        NavDisplay(
            backStack = backStack,
            onBack = { backStack.removeLastOrKeepIfSingle() },
            entryDecorators = listOf(
                // Add the default decorators for managing scenes and saving state
                rememberSceneSetupNavEntryDecorator(),
                rememberSavedStateNavEntryDecorator(),
                // Then add the view model store decorator
                rememberViewModelStoreNavEntryDecorator(),
            ),
            entryProvider = entryProvider {
                entry<NavRoute.Home> {
                    HomeScreen(
                        onNavigateToDetails = {
                            backStack.add(NavRoute.Details)
                        },
                    )
                }

                entry<NavRoute.Details> {
                    DetailsScreen(
                        onBack = {
                            backStack.removeLastOrKeepIfSingle()
                        },
                    )
                }
            },
            modifier = modifier,
        )
    }

    if (modalScreenController.sheet != null) {
        ModalBottomSheet(
            onDismissRequest = {},
            sheetState = sheetState,
            contentWindowInsets = { WindowInsets() },
            dragHandle = null,
            sheetGesturesEnabled = false,
        ) {
            modalScreenController.sheet?.invoke()
        }
    }
}

fun <T> MutableList<T>.removeLastOrKeepIfSingle(
    onSingle: (() -> Unit)? = null,
) {
    if (size == 1) {
        onSingle?.invoke()
        return
    }
    removeAt(size - 1)
}

sealed class NavRoute {
    data object Home : NavRoute()
    data object Details : NavRoute()
}

val LocalModalScreenController = staticCompositionLocalOf<ModalScreenController> {
    error("No ModalScreenController provided")
}

@OptIn(ExperimentalMaterial3Api::class)
@Stable
class ModalScreenController(
    private val scope: CoroutineScope,
    private val sheetState: SheetState,
) {

    var sheet: (@Composable () -> Unit)? by mutableStateOf(null)
        private set

    init {
        snapshotFlow { sheetState.isVisible }
            .onEach { isVisible ->
                println("ModalScreenController: isVisible = $isVisible")
                if (!isVisible) {
                    sheet = null
                }
            }
            .launchIn(scope)
    }

    fun showModal(
        content: @Composable () -> Unit,
    ) {
        sheet = content
    }

    fun hideModal() {
        scope.launch {
            sheetState.hide()
        }.invokeOnCompletion {
            sheet = null
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberModalScreenController(
    sheetState: SheetState,
    scope: CoroutineScope = rememberCoroutineScope(),
): ModalScreenController = remember { ModalScreenController(scope, sheetState) }

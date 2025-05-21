package com.example.navigation3.screen.licence

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.navigation3.core.ui.layout.BottomSheetLayout

@Composable
fun LicenceBottomScreen(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    BottomSheetLayout(
        title = "Licences",
        onDismissRequest = onDismissRequest,
        modifier = modifier,
    ) {
        Column {
            Text("Some licence text")

            Text("Some more licence text")
        }
    }
}

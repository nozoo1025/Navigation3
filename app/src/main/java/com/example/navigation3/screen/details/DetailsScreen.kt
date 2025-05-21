package com.example.navigation3.screen.details

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.navigation3.screen.licence.LicenceBottomScreen
import com.example.navigation3.screen.nav.LocalModalScreenController

@Composable
fun DetailsScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val modalScreenController = LocalModalScreenController.current

    Column(
        modifier = modifier,
    ) {
        Text("Details Screen")

        Button(
            onClick = onBack,
        ) {
            Text("Back")
        }

        Button(
            onClick = {
                modalScreenController.showModal {
                    LicenceBottomScreen(
                        onDismissRequest = { modalScreenController.hideModal() },
                    )
                }
            },
        ) {
            Text("Show Details Modal")
        }
    }
}

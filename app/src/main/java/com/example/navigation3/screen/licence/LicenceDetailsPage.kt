package com.example.navigation3.screen.licence

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LicenceDetails(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Text("""
            Apache License
            Version 2.0, January 2004
            http://www.apache.org/licenses/
            
            TERMS AND CONDITIONS FOR USE, REPRODUCTION, AND DISTRIBUTION
            1. Definitions.
            
            "Licensee" shall mean the copyright owner or entity authorized by the copyright owner that is granting the License.
            "Licensor" shall mean the copyright owner or entity authorized by the copyright owner that is granting the License.
            "You" (or "Your") shall mean an individual or Legal Entity exercising permissions granted by this License.
            "Source" form shall mean the preferred form for making modifications, including but not limited to software source code, documentation source, and configuration files.
        """.trimIndent())
    }
}

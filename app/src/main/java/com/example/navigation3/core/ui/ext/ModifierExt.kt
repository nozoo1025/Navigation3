package com.example.navigation3.core.ui.ext

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

fun Modifier.defaultVerticalPadding(): Modifier = this.padding(vertical = 16.dp)

fun Modifier.defaultHorizontalPadding(): Modifier = this.padding(horizontal = 16.dp)

fun Modifier.defaultPadding(): Modifier = this.padding(all = 16.dp)

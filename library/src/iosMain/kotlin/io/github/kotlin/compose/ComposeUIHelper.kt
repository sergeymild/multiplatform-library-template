package io.github.kotlin.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.ComposeUIViewController

/**
 * This function creates a UIViewController that contains the TestView Composable.
 * It can be used from Swift code to display the Compose UI.
 */
fun createTestViewController() = ComposeUIViewController(configure = {
    enforceStrictPlistSanityCheck = false
}) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Red
    ) {
        TestView()
    }
}
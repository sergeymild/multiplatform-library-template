package io.github.kotlin.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.kotlin.fibonacci.generateFibi

/**
 * A Composable function that displays a list of Fibonacci numbers.
 * This is a Compose Multiplatform component that works on all platforms.
 */
@Composable
fun TestView() {
    // Generate the first 20 Fibonacci numbers
    val fibonacciNumbers = remember {
        generateFibi().take(20).toList()
    }

    Column(
        modifier = Modifier.padding(16.dp)
            .fillMaxSize()
    ) {
        // Header
        Text(
            text = "Sequenc----2",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Using",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // List of Fibonacci numbers
        LazyColumn {
            items(fibonacciNumbers) { number ->
                FibonacciItem(number)
            }
        }
    }
}

/**
 * A Composable function that displays a single Fibonacci number in a card.
 */
@Composable
private fun FibonacciItem(number: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = number.toString(),
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(16.dp)
        )
    }
}

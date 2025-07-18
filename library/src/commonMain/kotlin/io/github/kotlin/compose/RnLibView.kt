package io.github.kotlin.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.kotlin.fibonacci.generateFibi

/** Простая функция из multiplatform library для демонстрации интеграции */
fun getLibraryData(): LibraryData {
  return LibraryData(
      title = "Fibonacci from Library!!!",
      subtitle = "Generated by Multiplatform Library",
      fibonacciNumbers = generateFibi().take(20).toList()
  )
}

/** Данные из library для отображения в React Native */
data class LibraryData(val title: String, val subtitle: String, val fibonacciNumbers: List<Int>)

/** Composable функция из multiplatform library */
@Composable
fun LibraryRnLibView() {
  val libraryData = remember { getLibraryData() }

  Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
      // Заголовок
      Text(
          text = libraryData.title + ")))",
          fontSize = 24.sp,
          fontWeight = FontWeight.Bold,
          color = Color.Blue,
          modifier = Modifier.padding(bottom = 8.dp)
      )

      // Подзаголовок
      Text(
          text = libraryData.subtitle,
          fontSize = 16.sp,
          color = Color.Gray,
          modifier = Modifier.padding(bottom = 16.dp)
      )

      // Список чисел Фибоначчи
      Card(
          modifier = Modifier.fillMaxWidth(),
          elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
      ) {
        LazyColumn(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
          items(libraryData.fibonacciNumbers) { number ->
            Text(text = "Fibonacci: $number", fontSize = 14.sp, color = Color.Green)
          }
        }
      }
    }
  }
}

package com.rnlib

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
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
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.kotlin.compose.getLibraryData

class RnLibView : FrameLayout {
  private val composeView: ComposeView

  constructor(context: Context?) : super(context!!) {
    composeView = ComposeView(context)
    setupCompose()
    addView(composeView)
  }

  constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {
    composeView = ComposeView(context, attrs)
    setupCompose()
    addView(composeView)
  }

  constructor(
      context: Context?,
      attrs: AttributeSet?,
      defStyleAttr: Int
  ) : super(context!!, attrs, defStyleAttr) {
    composeView = ComposeView(context, attrs, defStyleAttr)
    setupCompose()
    addView(composeView)
  }

  private fun setupCompose() {
    composeView.setViewCompositionStrategy(
        ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
    )
    // Используем настоящую функцию из multiplatform library
    composeView.setContent { RnLibViewCompose() }
  }
}

@Composable
private fun RnLibViewCompose() {
  // Получаем данные из multiplatform library
  val libraryData = remember { getLibraryData() }

  Column(modifier = Modifier.padding(16.dp).fillMaxSize()) {
    // Заголовок из library
    Text(
        text = libraryData.title,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(bottom = 8.dp)
    )

    Text(
        text = libraryData.subtitle,
        fontSize = 16.sp,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(bottom = 16.dp)
    )

    // Список чисел Фибоначчи из library
    LazyColumn { items(libraryData.fibonacciNumbers) { number -> FibonacciItem(number) } }
  }
}

/** Composable функция для отображения одного числа Фибоначчи в карточке. */
@Composable
private fun FibonacciItem(number: Int) {
  Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
    Text(
        text = number.toString(),
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium,
        modifier = Modifier.padding(16.dp)
    )
  }
}

package com.cmp

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import io.github.kotlin.compose.TestView

class CmpView : FrameLayout {
  private lateinit var composeView: ComposeView

  constructor(context: Context) : super(context) {
    initComposeView()
  }

  constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
    initComposeView()
  }

  constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
    context,
    attrs,
    defStyleAttr
  ) {
    initComposeView()
  }

  private fun initComposeView() {
    composeView = ComposeView(this.context).apply {
      setContent {
        MaterialTheme {
          Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
          ) {
            TestView()
          }
        }
      }
    }

    // Add the ComposeView to this FrameLayout
    addView(composeView)
  }
}

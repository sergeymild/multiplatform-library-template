package com.rnlib

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy

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
    // Используем полноценный Compose с интеграцией library проекта
    composeView.setContent { ComposeRnLibView() }
  }
}

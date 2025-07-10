package com.rnlib

import android.graphics.Color
import com.facebook.react.module.annotations.ReactModule
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.ViewManagerDelegate
import com.facebook.react.uimanager.annotations.ReactProp
import com.facebook.react.viewmanagers.RnLibViewManagerInterface
import com.facebook.react.viewmanagers.RnLibViewManagerDelegate

@ReactModule(name = RnLibViewManager.NAME)
class RnLibViewManager : SimpleViewManager<RnLibView>(),
  RnLibViewManagerInterface<RnLibView> {
  private val mDelegate: ViewManagerDelegate<RnLibView>

  init {
    mDelegate = RnLibViewManagerDelegate(this)
  }

  override fun getDelegate(): ViewManagerDelegate<RnLibView>? {
    return mDelegate
  }

  override fun getName(): String {
    return NAME
  }

  public override fun createViewInstance(context: ThemedReactContext): RnLibView {
    return RnLibView(context)
  }

  @ReactProp(name = "color")
  override fun setColor(view: RnLibView?, color: String?) {
    view?.setBackgroundColor(Color.parseColor(color))
  }

  companion object {
    const val NAME = "RnLibView"
  }
}

package com.cmp

import android.graphics.Color
import com.facebook.react.module.annotations.ReactModule
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.ViewManagerDelegate
import com.facebook.react.uimanager.annotations.ReactProp
import com.facebook.react.viewmanagers.CmpViewManagerInterface
import com.facebook.react.viewmanagers.CmpViewManagerDelegate

@ReactModule(name = CmpViewManager.NAME)
class CmpViewManager : SimpleViewManager<CmpView>(),
  CmpViewManagerInterface<CmpView> {
  private val mDelegate: ViewManagerDelegate<CmpView>

  init {
    mDelegate = CmpViewManagerDelegate(this)
  }

  override fun getDelegate(): ViewManagerDelegate<CmpView>? {
    return mDelegate
  }

  override fun getName(): String {
    return NAME
  }

  public override fun createViewInstance(context: ThemedReactContext): CmpView {
    return CmpView(context)
  }

  @ReactProp(name = "color")
  override fun setColor(view: CmpView?, color: String?) {
    view?.setBackgroundColor(Color.parseColor(color))
  }

  companion object {
    const val NAME = "CmpView"
  }
}

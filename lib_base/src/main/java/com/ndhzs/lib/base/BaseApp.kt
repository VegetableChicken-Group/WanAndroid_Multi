package com.ndhzs.lib.base

import android.app.Application
import androidx.annotation.CallSuper
import com.alibaba.android.arouter.launcher.ARouter
import com.ndhzs.lib.config.ConfigApplicationWrapper
import com.ndhzs.lib.utils.UtilsApplicationWrapper

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2022/5/26 14:01
 */
open class BaseApp : Application() {
  companion object {
    lateinit var baseApp: BaseApp
      private set
  }
  
  @CallSuper
  override fun onCreate() {
    super.onCreate()
    baseApp = this
    UtilsApplicationWrapper.setUtilsApplication(this)
    ConfigApplicationWrapper.setUtilsApplication(this)
    initARouter()
  }
  
  private fun initARouter() {
    if (BuildConfig.DEBUG) {
      ARouter.openLog()
      ARouter.openDebug()
    }
    ARouter.init(this)
  }
}
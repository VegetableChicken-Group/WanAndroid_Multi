package com.ndhzs.lib.config

import android.content.Context

/**
 * ...
 *
 * @author 985892345 (Guo Xiangrui)
 * @email guo985892345@foxmail.com
 * @date 2022/9/11 11:18
 */
object ConfigApplicationWrapper {
  
  internal lateinit var appContext: Context
    private set
  
  /**
   * 设置 lib_config 模块的 appContext
   */
  fun initializeAppContext(appContext: Context) {
    this.appContext = appContext
  }
}
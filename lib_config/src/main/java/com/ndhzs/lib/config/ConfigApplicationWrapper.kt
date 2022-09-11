package com.ndhzs.lib.config

import android.app.Application

/**
 * ...
 *
 * @author 985892345 (Guo Xiangrui)
 * @email guo985892345@foxmail.com
 * @date 2022/9/11 11:18
 */
object ConfigApplicationWrapper {
  
  internal lateinit var application: Application
    private set
  
  /**
   * 为了不反向依赖 lib_base，所以单独设置 lib_config 模块的 appContext
   */
  fun initialize(application: Application) {
    this.application = application
  }
}
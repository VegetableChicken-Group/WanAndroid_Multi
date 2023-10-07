package com.ndhzs.lib.config

import android.app.Application
import com.g985892345.provider.annotation.SingleImplProvider
import com.ndhzs.api.init.InitialManager
import com.ndhzs.api.init.InitialService

/**
 * ...
 *
 * @author 985892345 (Guo Xiangrui)
 * @email guo985892345@foxmail.com
 * @date 2022/9/11 11:18
 */
@SingleImplProvider(InitialService::class, "ConfigApplicationWrapper")
object ConfigApplicationWrapper : InitialService {
  
  internal lateinit var application: Application
    private set
  
  override fun onAllProcess(manager: InitialManager) {
    application = manager.application
  }
}
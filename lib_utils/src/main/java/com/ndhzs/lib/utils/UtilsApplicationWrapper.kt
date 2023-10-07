package com.ndhzs.lib.utils

import android.app.Application
import com.g985892345.provider.annotation.SingleImplProvider
import com.ndhzs.api.init.InitialManager
import com.ndhzs.api.init.InitialService

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email guo985892345@foxmail.com
 * @date 2022/8/1 12:50
 */
@SingleImplProvider(InitialService::class, "UtilsApplicationWrapper")
object UtilsApplicationWrapper : InitialService {
  
  internal lateinit var application: Application
    private set
  
  override fun onAllProcess(manager: InitialManager) {
    application = manager.application
  }
}
package com.ndhzs.lib.debug.crash

import com.g985892345.provider.annotation.SingleImplProvider
import com.ndhzs.api.init.InitialManager
import com.ndhzs.api.init.InitialService

/**
 * .
 *
 * @author 985892345
 * @date 2022/9/23 15:51
 */
@SingleImplProvider(InitialService::class, "CrashInitialService")
object CrashInitialService : InitialService {
  
  override fun onMainProcess(manager: InitialManager) {
    Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
      CrashActivity.start(
        throwable,
        manager.currentProcessName() ?: "未知",
        thread.name
      )
    }
  }
  
  override fun onOtherProcess(manager: InitialManager) {
    Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
      CrashActivity.start(
        throwable,
        manager.currentProcessName() ?: "未知",
        thread.name
      )
    }
  }
}
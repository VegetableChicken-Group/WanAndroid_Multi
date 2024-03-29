package com.ndhzs.lib.debug.crash

import com.google.auto.service.AutoService
import com.ndhzs.api.init.InitialManager
import com.ndhzs.api.init.InitialService

/**
 * .
 *
 * @author 985892345
 * @date 2022/9/23 15:51
 */
@AutoService(InitialService::class)
class CrashInitialService : InitialService {
  
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
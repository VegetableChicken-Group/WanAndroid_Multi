package com.ndhzs.lib.debug.crash

import com.google.auto.service.AutoService

/**
 * .
 *
 * @author 985892345
 * @date 2022/9/23 15:51
 */
@AutoService(com.ndhzs.api.init.InitialService::class)
class CrashInitialService : com.ndhzs.api.init.InitialService {
  
  override fun onMainProcess(manager: com.ndhzs.api.init.InitialManager) {
    Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
      CrashActivity.start(
        throwable,
        manager.currentProcessName() ?: "未知",
        thread.name
      )
    }
  }
  
  override fun onOtherProcess(manager: com.ndhzs.api.init.InitialManager) {
    Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
      CrashActivity.start(
        throwable,
        manager.currentProcessName() ?: "未知",
        thread.name
      )
    }
  }
}
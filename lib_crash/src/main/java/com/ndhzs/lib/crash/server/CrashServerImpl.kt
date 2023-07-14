package com.ndhzs.lib.crash.server

import android.app.Dialog
import com.g985892345.provider.annotation.SingleImplProvider
import com.ndhzs.api.crash.CRASH_SERVER
import com.ndhzs.api.crash.ICrashService
import com.ndhzs.lib.base.BaseApp
import com.ndhzs.lib.crash.dialog.CrashDialog

/**
 * .
 *
 * @author 985892345
 * 2023/3/1 10:26
 */
@SingleImplProvider(name = CRASH_SERVER)
object CrashServerImpl : ICrashService {
  
  override fun createCrashDialog(throwable: Throwable): Dialog {
    val topActivity = BaseApp.topActivity.get() ?: error("不存在栈顶 Activity，这是不应该出现的情况！")
    return CrashDialog.Builder(topActivity, throwable).build()
  }
}
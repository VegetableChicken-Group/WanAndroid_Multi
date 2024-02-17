package com.ndhzs.lib.single.ui

import android.annotation.SuppressLint
import android.os.Bundle
import com.ndhzs.api.account.IAccountService
import com.ndhzs.api.login.ILoginService
import com.ndhzs.lib.base.ui.BaseActivity
import com.ndhzs.lib.single.ISingleModuleEntry
import com.ndhzs.lib.utils.service.ServiceManager
import com.ndhzs.lib.utils.service.impl

/**
 * 用于单模块调试启动的 activity
 *
 * @author 985892345
 * @date 2023/9/7 00:13
 */
class SingleModuleActivity : BaseActivity() {

  private val mSingleModuleEntry by lazy {
    ServiceManager(ISingleModuleEntry::class)
  }

  override val isCancelStatusBar: Boolean
    get() = mSingleModuleEntry.isCancelStatusBar

  override val isPortraitScreen: Boolean
    get() = mSingleModuleEntry.isPortraitScreen

  @SuppressLint("SetTextI18n")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (mSingleModuleEntry.isNeedLogin) {
      val isLogin = IAccountService::class.impl
        .isLogin()
      if (!isLogin) {
        ILoginService::class.impl.start(this)
        safeFinish()
      } else {
        startCreatePage(savedInstanceState)
      }
    } else {
      startCreatePage(savedInstanceState)
    }
  }

  private fun startCreatePage(savedInstanceState: Bundle?) {
    when (val page = mSingleModuleEntry.getPage(this)) {
      is ISingleModuleEntry.FragmentPage -> {
        replaceFragment(android.R.id.content) {
          page.fragment.invoke(this@SingleModuleActivity)
        }
      }
      is ISingleModuleEntry.ActionPage -> {
        if (page.action.invoke(this) != null) {
          safeFinish()
        }
      }
    }
  }

  private var hasFinish = false

  private fun safeFinish() {
    if (hasFinish) return
    hasFinish = true
    mainLooper.queue.addIdleHandler {
      // 在队列空闲时 finish 当前 Activity
      // 防止另一个 activity 还没有完全打开
      finish()
      false
    }
  }
}
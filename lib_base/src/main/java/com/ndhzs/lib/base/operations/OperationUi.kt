package com.ndhzs.lib.base.operations

import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.ndhzs.api.account.IAccountService
import com.ndhzs.lib.config.route.LOGIN_ENTRY
import com.ndhzs.lib.utils.extensions.RxjavaLifecycle
import com.ndhzs.lib.utils.extensions.ToastUtils
import com.ndhzs.lib.utils.service.ServiceManager
import com.ndhzs.lib.utils.service.impl

/**
 *
 * 业务层的 Activity 和 Fragment 的共用函数
 *
 * @author 985892345 (Guo Xiangrui)
 * @email guo985892345@foxmail.com
 * @date 2022/8/8 20:58
 */
interface OperationUi : ToastUtils, RxjavaLifecycle {
  
  /**
   * 根布局
   */
  val rootView: View
  
  /**
   * View 的 LifecycleOwner
   */
  fun getViewLifecycleOwner(): LifecycleOwner
  
  /**
   * 如果没有登录则会引导去登录界面
   */
  fun doIfLogin(msg: String? = "此功能", next: () -> Unit) {
    val accountService = IAccountService::class.impl
    if (accountService.isLogin()) {
      next()
    } else {
      toast("${msg}登录过后才能使用哦~")
      ServiceManager.activity(LOGIN_ENTRY)
    }
  }
}
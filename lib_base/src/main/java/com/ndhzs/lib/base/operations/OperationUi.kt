package com.ndhzs.lib.base.operations

import com.ndhzs.api.account.IAccountService
import com.ndhzs.lib.base.ui.BaseUi
import com.ndhzs.lib.config.route.LOGIN_ENTRY
import com.ndhzs.lib.utils.service.ServiceManager
import com.ndhzs.lib.utils.service.impl


/**
 *
 * 业务层的 Activity 和 Fragment 的共用函数
 *
 * ## 一、doIfLogin()
 * ```
 * doIfLogin {
 *     // 判断是否已经登录，只有登录了执行，未登录时会弹窗提示去登录界面
 * }
 * ```
 *
 *
 *
 *
 *
 *
 * # 更多封装请往父类和接口查看
 * @author 985892345 (Guo Xiangrui)
 * @email guo985892345@foxmail.com
 * @date 2022/8/8 20:58
 */

/**
 * 如果没有登录则会引导去登录界面
 */
fun BaseUi.doIfLogin(msg: String? = "此功能", next: () -> Unit) {
  val accountService = IAccountService::class.impl
  if (accountService.isLogin()) {
    next()
  } else {
    toast("${msg}登录过后才能使用哦~")
    ServiceManager.activity(LOGIN_ENTRY)
  }
}
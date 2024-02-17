package com.ndhzs.module.login.service

import android.content.Context
import android.content.Intent
import com.alibaba.android.arouter.facade.annotation.Route
import com.ndhzs.api.login.ILoginService
import com.ndhzs.lib.config.route.LOGIN_SERVICE
import com.ndhzs.module.login.page.ui.LoginActivity

/**
 * .
 *
 * @author 985892345
 * @date 2024/2/15 16:16
 */
@Route(path = LOGIN_SERVICE)
class LoginServiceImpl : ILoginService {

  override fun start(context: Context) {
    context.startActivity(Intent(context, LoginActivity::class.java))
  }

  override fun init(context: Context?) {
  }

}
package com.ndhzs.module.login.page.ui

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ndhzs.lib.common.config.LOGIN_ENTRY
import com.ndhzs.lib.common.ui.mvvm.BaseVmBindActivity
import com.ndhzs.module.login.R
import com.ndhzs.module.login.databinding.ActivityLoginBinding
import com.ndhzs.module.login.page.viewmodel.LoginViewModel

@Route(path = LOGIN_ENTRY)
class LoginActivity : BaseVmBindActivity<LoginViewModel, ActivityLoginBinding>() {
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_login)
    initClick()
  }
  
  private fun initClick() {
    
  }
}
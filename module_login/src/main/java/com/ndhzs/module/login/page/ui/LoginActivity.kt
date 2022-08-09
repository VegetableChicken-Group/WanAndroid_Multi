package com.ndhzs.module.login.page.ui

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ndhzs.lib.base.ui.mvvm.BaseVmBindActivity
import com.ndhzs.lib.config.route.LOGIN_ENTRY
import com.ndhzs.lib.utils.extensions.setOnSingleClickListener
import com.ndhzs.module.login.databinding.ActivityLoginBinding
import com.ndhzs.module.login.page.viewmodel.LoginViewModel
import com.ndhzs.module.login.utils.textwatcher.BaseTextWatcher

@Route(path = LOGIN_ENTRY)
class LoginActivity : BaseVmBindActivity<LoginViewModel, ActivityLoginBinding>() {
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    initView()
    initClick()
    initEvent()
    initObserve()
  }
  
  private fun initView() {
    binding.loginCbRemember.isChecked = viewModel.isRememberPassword()
  }
  
  private fun initClick() {
    binding.loginCbRemember.setOnCheckedChangeListener { _, isChecked ->
      viewModel.changeRememberPassword(isChecked)
    }
    
    binding.loginBtnLogin.setOnSingleClickListener {
      val username = binding.loginEtUsername.text?.toString()
      val password = binding.loginEtPassword.text?.toString()
      if (username.isNullOrBlank() || password.isNullOrBlank()) {
        toast("请输入完整!")
      } else {
        viewModel.login(username, password)
      }
    }
    
    binding.loginBtnRegister.setOnSingleClickListener {
    
    }
    
    binding.loginBtnForgetPassword.setOnSingleClickListener {
      toast("wanAndroid好像没得这个功能")
    }
    
    binding.loginBtnQq.setOnSingleClickListener {
      toast("摆设作用，未实现")
    }
    
    binding.loginBtnWechat.setOnSingleClickListener {
      toast("摆设作用，未实现")
    }
  }
  
  private fun initEvent() {
    binding.loginEtUsername.addTextChangedListener(BaseTextWatcher(binding.loginTilUsername))
    binding.loginEtPassword.addTextChangedListener(BaseTextWatcher(binding.loginTilPassword))
  }
  
  private fun initObserve() {
    viewModel.userName.observe {
      binding.loginEtUsername.setText(it)
    }
    
    viewModel.password.observe {
      binding.loginEtPassword.setText(it)
    }
    
    viewModel.loginEvent.collectSuspend {
      when (it) {
        is LoginViewModel.LoginEvent.ApiFail -> {
          toast(it.error.errorMsg)
        }
        is LoginViewModel.LoginEvent.HttpFail -> {
          toast(it.error.message ?: "未知错误")
        }
        is LoginViewModel.LoginEvent.Success -> {
          toast("欢迎回来 ${it.bean.username}")
        }
      }
    }
  }
}
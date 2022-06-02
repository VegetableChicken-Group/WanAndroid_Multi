package com.ndhzs.module.login.page.viewmodel

import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.distinctUntilChanged
import com.ndhzs.api.account.IAccountService
import com.ndhzs.lib.common.extensions.asFlow
import com.ndhzs.lib.common.extensions.getSp
import com.ndhzs.lib.common.network.ApiException
import com.ndhzs.lib.common.service.ServiceManager
import com.ndhzs.lib.common.ui.mvvm.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.catch

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2022/5/29 21:48
 */
class LoginViewModel : BaseViewModel() {
  
  private val _username = MutableLiveData<String?>()
  val userName: LiveData<String?>
    get() = _username.distinctUntilChanged()
  
  private val _password = MutableLiveData<String?>()
  val password: LiveData<String?>
    get() = _password.distinctUntilChanged()
  
  private val _loginEvent = MutableSharedFlow<LoginEvent>()
  val loginEvent: SharedFlow<LoginEvent>
    get() = _loginEvent
  
  private val mLoginSp = appContext.getSp(this::class.java.simpleName)
  private val mAccountService = ServiceManager(IAccountService::class)
  
  init {
    mAccountService.observeUserInfoFlow()
      .collectLaunch {
        if (it == null) {
          // 此时登出了账号，取消记住密码
          changeRememberPassword(false)
        }
        _username.value = it?.username
        if (isRememberPassword()) {
          _password.value = it?.password
        }
      }
  }
  
  fun isRememberPassword(): Boolean {
    return mLoginSp.getBoolean("isRememberPassword", false)
  }
  
  fun changeRememberPassword(isRemember: Boolean) {
    mLoginSp.edit {
      putBoolean("isRememberPassword", isRemember)
    }
  }
  
  fun login(username: String, password: String) {
    mAccountService.login(username, password)
      .asFlow()
      .catch {
        if (it is ApiException) {
          _loginEvent.emit(LoginEvent.ApiFail(it))
        } else {
          _loginEvent.emit(LoginEvent.HttpFail(it))
        }
      }.collectLaunch {
        _loginEvent.emit(LoginEvent.Success(it))
      }
  }
  
  sealed class LoginEvent {
    data class Success(val bean: IAccountService.LoginBean): LoginEvent()
    data class ApiFail(val error: ApiException): LoginEvent()
    data class HttpFail(val error: Throwable): LoginEvent()
  }
}
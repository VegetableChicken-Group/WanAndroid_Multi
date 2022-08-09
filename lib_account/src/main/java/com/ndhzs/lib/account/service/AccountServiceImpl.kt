package com.ndhzs.lib.account.service

import android.content.Context
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.gson.Gson
import com.ndhzs.api.account.ACCOUNT_SERVICE
import com.ndhzs.api.account.IAccountService
import com.ndhzs.api.account.ICookieService
import com.ndhzs.lib.account.network.LoginApiService
import com.ndhzs.lib.utils.extensions.getSp
import com.ndhzs.lib.utils.extensions.lazyUnlock
import com.ndhzs.lib.utils.extensions.mapOrThrowApiException
import com.ndhzs.lib.utils.extensions.throwApiExceptionIfFail
import com.ndhzs.lib.utils.network.ApiGenerator
import com.ndhzs.lib.utils.network.getBaseUrl
import com.ndhzs.lib.utils.service.impl
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2022/5/29 22:25
 */
@Route(path = ACCOUNT_SERVICE)
class AccountServiceImpl : IAccountService {
  
  private lateinit var mContext: Context

  private val mCookieService = ICookieService::class.impl
  
  private val mApiService by lazyUnlock {
    ApiGenerator.getApiService(LoginApiService::class, false) {
      client(
        OkHttpClient().newBuilder()
          .cookieJar(mCookieService)
          .build()
      )
    }
  }
  
  private val mUserInfoLiveData = MutableLiveData<IAccountService.LoginBean?>()
  
  override fun getUserInfoLiveData(): LiveData<IAccountService.LoginBean?> {
    return mUserInfoLiveData
  }
  
  override fun isLogin(): Boolean {
    return mCookieService.loadForRequest(getBaseUrl().toHttpUrl()).isNotEmpty()
  }
  
  override fun login(
    username: String,
    password: String
  ): Single<IAccountService.LoginBean> {
    return mApiService.login(username, password)
      .mapOrThrowApiException()
      .doOnSuccess {
        // 网络请求来的不默认包含密码，所以自己加上
        mUserInfoLiveData.postValue(it.copy(password = password))
      }.subscribeOn(Schedulers.io())
  }
  
  override fun logout(): Completable {
    return mApiService.logout()
      .throwApiExceptionIfFail()
      .doOnSuccess {
        mCookieService.clearCookie()
        mUserInfoLiveData.postValue(null)
      }.flatMapCompletable {
        Completable.complete()
      }.subscribeOn(Schedulers.io())
  }
  
  override fun register(
    username: String,
    password: String,
    rePassword: String
  ): Single<IAccountService.LoginBean> {
    return mApiService.register(username, password, rePassword)
      .mapOrThrowApiException()
      .doOnSuccess {
        // 网络请求来的不默认包含密码，所以自己加上
        mUserInfoLiveData.postValue(it.copy(password = password))
      }.subscribeOn(Schedulers.io())
  }
  
  override fun init(context: Context) {
    mContext = context
    val userinfoSp = context.getSp("UserInfo")
    val gson = Gson()
    val spKey = "LoginBean"
    // 从本地初始化数据
    val userinfo = userinfoSp.getString(spKey, null)
    val loginBean: IAccountService.LoginBean? =
      gson.fromJson(userinfo, IAccountService.LoginBean::class.java)
    if (loginBean != null) {
      mUserInfoLiveData.postValue(loginBean)
    }
    getUserInfoLiveData().observeForever {
      userinfoSp.edit {
        putString(spKey, gson.toJson(it))
      }
    }
  }
}
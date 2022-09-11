package com.ndhzs.lib.account.service

import android.content.Context
import androidx.core.content.edit
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.ndhzs.api.account.ACCOUNT_SERVICE
import com.ndhzs.api.account.IAccountService
import com.ndhzs.lib.account.network.LoginApiService
import com.ndhzs.lib.utils.extensions.Value
import com.ndhzs.lib.utils.extensions.getSp
import com.ndhzs.lib.utils.extensions.lazyUnlock
import com.ndhzs.lib.utils.extensions.unsafeSubscribeBy
import com.ndhzs.lib.utils.network.*
import com.ndhzs.lib.utils.service.impl
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import okhttp3.HttpUrl.Companion.toHttpUrl

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2022/5/29 22:25
 */
@Route(path = ACCOUNT_SERVICE)
class AccountServiceImpl : IAccountService {
  
  private lateinit var mContext: Context

  private val mCookieService = CookieServiceImpl::class.impl
  
  private val mApiService by lazyUnlock {
    ApiGenerator.getNewRetrofit(false) {
      cookieJar(mCookieService)
    }.create(LoginApiService::class.java)
  }
  
  override fun observeUserInfoState(): Observable<Value<IAccountService.LoginBean>> {
    return userInfoState.distinctUntilChanged()
  }
  
  override fun observeUserInfoEvent(): Observable<Value<IAccountService.LoginBean>> {
    return userInfoEvent.distinctUntilChanged()
  }
  
  private val userInfoState = BehaviorSubject.create<Value<IAccountService.LoginBean>>()
  private val userInfoEvent = PublishSubject.create<Value<IAccountService.LoginBean>>()
  
  private fun emitUserInfo(bean: IAccountService.LoginBean?) {
    val value = Value(bean)
    userInfoState.onNext(value)
    userInfoEvent.onNext(value)
  }
  
  override fun isLogin(): Boolean {
    return mCookieService.loadForRequest(getBaseUrl().toHttpUrl()).isNotEmpty()
  }
  
  override fun login(
    username: String,
    password: String
  ): Single<ApiWrapper<IAccountService.LoginBean>> {
    return mApiService.login(username, password)
      .doOnSuccess {
        if (it.isSuccess()) {
          // 网络请求来的不默认包含密码，所以自己加上
          emitUserInfo(it.data.copy(password = password))
        }
      }.subscribeOn(Schedulers.io())
  }
  
  override fun logout(): Completable {
    return mApiService.logout()
      .throwApiExceptionIfFail()
      .doOnSuccess {
        mCookieService.clearCookie()
        emitUserInfo(null)
      }.flatMapCompletable {
        Completable.complete()
      }.subscribeOn(Schedulers.io())
  }
  
  override fun register(
    username: String,
    password: String,
    rePassword: String
  ): Single<ApiWrapper<IAccountService.LoginBean>> {
    return mApiService.register(username, password, rePassword)
      .doOnSuccess {
        if (it.isSuccess()) {
          // 网络请求来的不默认包含密码，所以自己加上
          emitUserInfo(it.data.copy(password = password))
        }
      }.subscribeOn(Schedulers.io())
  }
  
  override fun init(context: Context) {
    mContext = context
    val userinfoSp = context.getSp("UserInfo")
    val gson = Gson()
    val spKey = "登录数据"
    // 从本地初始化数据
    val userinfo = userinfoSp.getString(spKey, null)
    try {
      val loginBean: IAccountService.LoginBean? =
        gson.fromJson(userinfo, IAccountService.LoginBean::class.java)
      if (loginBean != null) {
        emitUserInfo(loginBean)
      }
    } catch (e: JsonSyntaxException) {
      e.printStackTrace()
      userinfoSp.edit { remove(spKey) }
    }
    observeUserInfoEvent()
      .unsafeSubscribeBy {
        userinfoSp.edit {
          putString(spKey, gson.toJson(it))
        }
      }
  }
}
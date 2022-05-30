package com.ndhzs.api.account

import androidx.lifecycle.LiveData
import com.alibaba.android.arouter.facade.template.IProvider
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import java.io.Serializable

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2022/5/29 22:17
 */
interface IAccountService : IProvider, CookieJar {
  
  /**
   * 回调的数据为 null 时，说明退出了登录
   */
  fun getUserInfoLiveData(): LiveData<LoginBean?>
  
  fun isLogin(): Boolean
  
  fun login(
    username: String,
    password: String
  ): Single<LoginBean>
  
  fun logout(): Completable
  
  fun register(
    username: String,
    password: String,
    rePassword: String
  ): Single<LoginBean>
  
  data class LoginBean(
    val admin: Boolean,
    val chapterTops: List<Any>,
    val coinCount: Int,
    val collectIds: List<Int>,
    val email: String,
    val icon: String,
    val id: Int,
    val nickname: String,
    val password: String,
    val publicName: String,
    val token: String,
    val type: Int,
    val username: String
  ) : Serializable
}
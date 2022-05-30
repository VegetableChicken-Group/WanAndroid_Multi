package com.ndhzs.api.account

import androidx.lifecycle.*
import com.alibaba.android.arouter.facade.template.IProvider
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import okhttp3.CookieJar
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
  
  /**
   * 观察学生信息
   *
   * 回调的数据为 null 时，说明退出了登录，因为 Rxjava 不能传递 null，所以只能使用 Result 来包装一层
   */
  fun observeUserInfo(lifecycleOwner: LifecycleOwner): Observable<Result<LoginBean?>> {
    return Observable.fromPublisher(
      getUserInfoLiveData().map {
        Result.success(it)
      }.toPublisher(lifecycleOwner)
    )
  }
  
  /**
   * 观察学生信息
   *
   * 回调的数据为 null 时，说明退出了登录
   */
  fun observeUserInfo(): Flow<LoginBean?> {
    return getUserInfoLiveData().asFlow()
  }
  
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
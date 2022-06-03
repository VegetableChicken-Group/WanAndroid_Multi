package com.ndhzs.api.account

import androidx.lifecycle.*
import com.alibaba.android.arouter.facade.template.IProvider
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMap
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.rx3.asObservable
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
   * 1、由于 Observable 不允许 null 值，所以使用了 Result 来进行了一层包装
   *
   * 2、注意：使用时一定要注意生命周期问题，尤其是 Fragment 一般使用 **ViewLifecycleOwner**
   * - 在 Activity 和 Fragment 中建议使用 [observeUserInfoFlow]，Flow 更容易配合生命周期，防止内存泄漏
   * - 在 ViewModel 中也推荐使用 [observeUserInfoFlow]
   *
   * 3、如果你想对于不同信息返回给下游不同的 Observable，建议使用 switchMap，因为它可以自动停止上一个发送的 Observable
   * ```
   * 写法如下：
   *    observeUserInfo(lifecycleOwner)
   *      .switchMap {
   *        // switchMap 可以在上游发送新的数据时自动关闭上一次数据生成的 Observable
   *        val stuNum = it.getOrNull()
   *        if (stuNum.isEmpty()) {
   *          Observable.never()
   *        } else {
   *          LessonDataBase.INSTANCE.getStuLessonDao() // 数据库
   *            .observeAllLesson(stuNum) // 观察数据库的数据变动，这是 Room 的响应式编程
   *            .subscribeOn(Schedulers.io())
   *            .distinctUntilChanged() // 必加，因为 Room 每次修改都会回调，所以需要加这个去重
   *            .doOnSubscribe { getLesson(stuNum, isNeedOldList).safeSubscribeBy() } // 在开始订阅时请求一次云端数据
   *            .map { StuResult(stuNum, it) }
   *        }
   *      }
   * ```
   * 4、在 ViewModel 中使用存在无法得到 lifecycleOwner 的问题，可以使用 [observeUserInfoUnsafe]，
   *
   * 回调的数据为 null 时，说明退出了登录
   */
  fun observeUserInfo(lifecycleOwner: LifecycleOwner): Observable<Result<LoginBean?>> {
    return Observable.fromPublisher(
      getUserInfoLiveData().map {
        Result.success(it)
      }.toPublisher(lifecycleOwner)
    )
  }
  
  /**
   * 官方提供的 LiveData.toPublisher 必须要 LifecycleOwner，
   * 很奇怪，我看他源码除了拿这个去调用 LiveData.observe 方法外也没干啥其他事情，
   * 为什么他不额外提供一个调用 LiveData.observeForever 的呢？
   *
   * 只能自己用 Flow 中途转换一遍了
   *
   * 回调的数据为 null 时，说明退出了登录
   *
   * 注意生命周期问题！
   */
  fun observeUserInfoUnsafe(): Observable<Result<LoginBean?>> {
    return observeUserInfoFlow().map { Result.success(it) }.asObservable()
  }
  
  /**
   * 观察学生信息
   *
   * 1、Flow 更适合在 ViewModel 层、 Activity 和 Fragment 层 使用
   *
   * 2、对于 Repository 层更推荐使用 [observeUserInfo]，因为 Flow 远不及 Rxjava 好处理复杂的数据流
   *
   * 3、如果你想对于不同学号返回给下游不同的 Flow，操作符为 switchMap（flatMap 并不会自动关闭上一个转换的 Flow），
   * 但目前该操作符被废弃，新操作符 flatMapLatest 还在测验阶段，
   * 所以复杂数据流更推荐使用 [observeUserInfo] 来处理
   *
   * 回调的数据为 null 时，说明退出了登录
   */
  fun observeUserInfoFlow(): Flow<LoginBean?> {
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
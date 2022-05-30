package com.ndhzs.lib.common.network

import android.util.Log
import com.ndhzs.api.account.IAccountService
import com.ndhzs.lib.common.extensions.lazyUnlock
import com.ndhzs.lib.common.service.ServiceManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.reflect.KClass

/**
 * ```
 * 在 ApiService 接口中：
 * interface ApiService {
 *
 *     @GET("/aaa/bbb")
 *     fun getXXX(): Single<ApiWrapper<Bean>> // 统一使用 ApiWrapper 包装
 *
 *     companion object {
 *         val INSTANCE by lazy {
 *             ApiGenerator.getApiService(ApiService::class)
 *         }
 *     }
 * }
 *
 *
 * 示例代码：
 * ApiService.INSTANCE.getXXX()
 *     .subscribeOn(Schedulers.io())  // 线程切换
 *     .observeOn(AndroidSchedulers.mainThread())
 *     .catchApiExceptionOrMap {      // 当 errorCode 的值不为成功时抛错，并处理错误
 *         // 处理 ApiException 错误
 *     }
 *     .safeSubscribeBy {             // 如果是网络连接错误，则这里会默认处理
 *         // 成功的时候
 *     }
 *     .lifeCycle() // ViewModel 中带有的自动回收，建议加上，但 ViewModel 对于 safeSubscribeBy 已经默认添加
 * ```
 *
 *
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2022/5/29 22:30
 */
object ApiGenerator {
  
  fun <T : Any> getApiService(clazz: KClass<T>): T {
    return retrofit.create(clazz.java)
  }
  
  fun <T : Any> getApiService(
    clazz: KClass<T>,
    isNeedCookie: Boolean,
    config: Retrofit.Builder.() -> Unit
  ): T {
    return getNewRetrofit(isNeedCookie, config).create(clazz.java)
  }
  
  fun getNewRetrofit(
    isNeedCookie: Boolean,
    config: Retrofit.Builder.() -> Unit
  ): Retrofit {
    return Retrofit
      .Builder()
      .baseUrl(getBaseUrl())
      .client(OkHttpClient().newBuilder().defaultOkhttpConfig(isNeedCookie))
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJava3CallAdapterFactory.createSynchronous())
      .apply { config.invoke(this) }
      .build()
  }
  
  private val retrofit by lazyUnlock {
    getNewRetrofit(true) {}
  }
  
  private val mAccountService by lazyUnlock {
    ServiceManager(IAccountService::class)
  }
  
  private fun OkHttpClient.Builder.defaultOkhttpConfig(isNeedCookie: Boolean): OkHttpClient {
    connectTimeout(10, TimeUnit.SECONDS)
    readTimeout(10, TimeUnit.SECONDS)
    if (isNeedCookie) {
      cookieJar(mAccountService) // 给每条请求增加 cookie
    }
    return build()
  }
}
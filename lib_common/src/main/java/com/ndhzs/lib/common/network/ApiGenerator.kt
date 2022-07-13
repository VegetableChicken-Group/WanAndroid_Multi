package com.ndhzs.lib.common.network

import com.ndhzs.api.account.IAccountService
import com.ndhzs.lib.common.service.ServiceManager
import com.ndhzs.lib.common.extensions.mapOrThrowApiException
import com.ndhzs.lib.common.extensions.mapOrCatchApiException
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.reflect.KClass

/**
 * # 用法
 * ## 命名规则
 * XXXApiService 接口，命名规则，以 ApiService 结尾
 *
 * ## 接口模板
 * ```
 * interface XXXApiService {
 *
 *     @GET("/aaa/bbb")
 *     fun getXXX(): Single<ApiWrapper<Bean>>
 *     // 统一使用 ApiWrapper 或 ApiStatus 包装，注意 Bean 类要去掉 data 字段，不然会报 json 错误
 *
 *     companion object {
 *         val INSTANCE by lazy {
 *             ApiGenerator.getXXXApiService(XXXApiService::class)
 *         }
 *     }
 * }
 * ```
 *
 * ## 示例代码
 * ```
 * ApiService.INSTANCE.getXXX()
 *     .subscribeOn(Schedulers.io())  // 线程切换
 *     .observeOn(AndroidSchedulers.mainThread())
 *     .mapOrCatchApiException {      // 当 errorCode 的值不为成功时抛错，并处理错误
 *         // 处理 ApiException 错误，注意：这里并不会处理断网时的 HttpException
 *     }
 *     .safeSubscribeBy {            // 如果是网络连接错误，则这里会默认处理
 *         // 成功的时候
 *     }
 * ```
 *
 * # 其他注意事项
 * ## Rxjava 或 Flow 的下游没任何输出（怎么处理断网时的 HttpException）
 *
 * 大概率是你直接用了 [mapOrCatchApiException]，而它只会处理 [ApiException]，如果你要处理其他网络错误，
 * 请把 [mapOrCatchApiException] 替换为 [mapOrThrowApiException]：
 * ```
 *     .mapOrThrowApiException()
 *     .doOnError {                    // Flow 操作符为 catch
 *         if (it is ApiException) {
 *             // 处理 ApiException 错误
 *         } else {
 *             // 处理其他网络错误
 *         }
 *     }
 * ```
 *
 *
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2022/5/29 22:30
 */
object ApiGenerator {
  
  private val mAccountService = ServiceManager(IAccountService::class)
  private val retrofit = getNewRetrofit(true) {}
  
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
  
  private fun OkHttpClient.Builder.defaultOkhttpConfig(isNeedCookie: Boolean): OkHttpClient {
    connectTimeout(10, TimeUnit.SECONDS)
    readTimeout(10, TimeUnit.SECONDS)
    if (isNeedCookie) {
      cookieJar(mAccountService) // 给每条请求增加 cookie
    }
    addInterceptor(HttpLoggingInterceptor())
    return build()
  }
}
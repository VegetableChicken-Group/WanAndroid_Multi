package com.ndhzs.lib.utils.network

import com.ndhzs.lib.config.route.COOKIE_SERVICE
import com.ndhzs.lib.utils.BuildConfig
import com.ndhzs.lib.utils.service.ServiceManager
import okhttp3.CookieJar
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.CopyOnWriteArrayList
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
 *
 * 或者：
 * interface XXXApiService : IApi
 *
 * XXXApiService::class.impl
 *     .getXXX()
 * ```
 *
 * ## 示例代码
 * ```
 * ApiService.INSTANCE.getXXX()
 *     .subscribeOn(Schedulers.io())  // 线程切换
 *     .observeOn(AndroidSchedulers.mainThread())
 *     .mapOrInterceptException {     // 当 errorCode 的值不为成功时抛错，并拦截异常
 *         // 这里面可以使用 DSL 写法，更多详细用法请看该方法注释
 *     }
 *     .safeSubscribeBy {            // 如果是网络连接错误，则这里会默认处理
 *         // 成功的时候
 *         // 如果是仓库层，请使用 unsafeSubscribeBy()
 *     }
 * ```
 *
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2022/5/29 22:30
 */
object ApiGenerator {
  
  private val mCookieJar = ServiceManager<CookieJar>(COOKIE_SERVICE)
  private val retrofit = getNewRetrofit(true)
  
  fun <T : Any> getApiService(clazz: KClass<T>): T {
    return retrofit.create(clazz.java)
  }
  
  /**
   * 创建一个新的 Retrofit
   */
  fun getNewRetrofit(
    isNeedCookie: Boolean,
    config: (OkHttpClient.Builder.() -> Unit)? = null
  ): Retrofit {
    return Retrofit
      .Builder()
      .baseUrl(getBaseUrl())
      .client(OkHttpClient().newBuilder().run {
        config?.invoke(this)
        defaultOkhttpConfig(isNeedCookie)
      })
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJava3CallAdapterFactory.createSynchronous())
      .build()
  }
  
  private fun OkHttpClient.Builder.defaultOkhttpConfig(isNeedCookie: Boolean): OkHttpClient {
    connectTimeout(10, TimeUnit.SECONDS)
    readTimeout(10, TimeUnit.SECONDS)
    if (isNeedCookie) {
      cookieJar(mCookieJar) // 给每条请求增加 cookie
    }
    addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
    if (BuildConfig.DEBUG) {
      addInterceptor {
        // 专门用于收集信息的拦截器
        val request = it.request()
        val apiResult = ApiResult(request.newBuilder().build())
        apiResultList.add(apiResult)
        try {
          it.proceed(request).also { response ->
            // 如果请求成功了就记录新的 request
            apiResult.request = response.request.newBuilder().build()
            apiResult.response = response.newBuilder().build()
          }
        } catch (e: Exception) {
          apiResult.stackTrace = e.stackTraceToString()
          throw e
        }
      }
    }
    return build()
  }
  
  // 用于 debug 时保存网络请求，因为异常触发时进程被摧毁，Pandora 记录的请求会被清空
  val apiResultList by lazy {
    CopyOnWriteArrayList<ApiResult>()
  }
  
  class ApiResult(var request: Request) {
    var response: Response? = null
    var stackTrace: String? = null // 直接保存 throwable 对象的话，不会被记录下来，不知道为什么
  }
}

interface IApi {
  companion object {
    val MAP = HashMap<KClass<out IApi>, IApi>()
  }
}

/**
 * 带 cookie 的请求
 */
@Suppress("UNCHECKED_CAST")
val <I : IApi> KClass<I>.api: I
  get() = IApi.MAP.getOrPut(this) {
    ApiGenerator.getApiService(this)
  } as I
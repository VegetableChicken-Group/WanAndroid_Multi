package com.ndhzs.lib.utils.service

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment
import com.g985892345.provider.manager.KtProviderManager
import com.ndhzs.lib.utils.extensions.appContext
import kotlin.reflect.KClass

/**
 * 对服务获取的封装，便于以后修改为其他依赖注入的框架，建议都通过该文件提供的方法获取服务，
 * 不采用 @Autowired 的方式，便于以后更换实现。
 * 使用方法：
 *     1. 在service包中创建对应的服务接口并继承IProvider接口，命名请加上代表接口的I前缀和Service后缀，例如IAccountService；
 *     2. 创建该接口的实现类，命名尽量只去掉I即可，然后加上路由注解，路由地址统一写到RoutingTable中，例如AccountService；
 *     3. 通过ServiceManager的方式获取实现类。
 */
object ServiceManager {
  
  /**
   * 写法：
   * ```
   * ServiceManger(IAccountService::class)
   *   .isLogin()
   * ```
   */
  operator fun <T : Any> invoke(serviceClass: KClass<T>): T = getImplOrThrow(serviceClass)
  
  /**
   * 写法：
   * ```
   * ServiceManger<IAccountService>(ACCOUNT_SERVICE)
   *   .isLogin()
   * ```
   */
  operator fun <T : Any> invoke(servicePath: String): T = getImplOrThrow(servicePath)
  
  fun fragment(servicePath: String): Fragment = getImplOrThrow(servicePath, false)
  
  fun activity(servicePath: String) {
    val activityKClass = getKClassOrThrow<Activity>(servicePath)
    appContext.startActivity(
      Intent(appContext, activityKClass.java)
        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    )
  }
  
  fun <T : Any> getAllSingleImpl(clazz: KClass<T>): Map<String, () -> T> =
    KtProviderManager.getAllSingleImpl(clazz)
  
  fun <T : Any> getAllNewImpl(clazz: KClass<T>): Map<String, () -> T> =
    KtProviderManager.getAllNewImpl(clazz)
  
  fun <T : Any> getImplOrNull(name: String, singleton: Boolean? = null): T? =
    KtProviderManager.getImplOrNull(name, singleton)
  fun <T : Any> getImplOrNull(clazz: KClass<T>, singleton: Boolean? = null): T? =
    KtProviderManager.getImplOrNull(clazz, singleton)
  fun <T : Any> getImplOrNull(clazz: KClass<T>, name: String = "", singleton: Boolean? = null): T? =
    KtProviderManager.getImplOrNull(clazz, name, singleton)
  
  fun <T : Any> getImplOrThrow(name: String, singleton: Boolean? = null): T =
    KtProviderManager.getImplOrThrow(name, singleton)
  fun <T : Any> getImplOrThrow(clazz: KClass<T>, singleton: Boolean? = null): T =
    KtProviderManager.getImplOrThrow(clazz, singleton)
  fun <T : Any> getImplOrThrow(clazz: KClass<T>, name: String = "", singleton: Boolean? = null): T =
    KtProviderManager.getImplOrThrow(clazz, name, singleton)
  
  fun <T : Any> getKClassOrNull(name: String): KClass<out T>? =
    KtProviderManager.getKClassOrNull(name)
  fun <T : Any> getKClassOrThrow(name: String): KClass<out T> =
    KtProviderManager.getKClassOrThrow(name)
}

/**
 * 写法：
 * ```
 * IAccountService::class.impl
 *   .isLogin()
 * ```
 */
val <T: Any> KClass<T>.impl: T
  get() = ServiceManager.getImplOrThrow(this)

fun <T: Any> KClass<T>.impl(name: String): T = ServiceManager.getImplOrThrow(this, name)

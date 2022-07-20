package com.ndhzs.lib.common.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

/**
 * 用于代替 lifecycleScope.launch 的更好的方法
 *
 * 有以下作用：
 * - 返回 [LaunchCatcher]，如果你想捕获异常，可以直接调用 .catch()，并且在你不捕获异常时会将异常往上抛出
 *
 * ## 背景：
 * 如果你想抓取 launch 的异常，只能像下面这样写
 * ```
 * lifecycleScope.launch(
 *   CoroutineExceptionHandler { context, exception ->
 *     exception.printStackTrace()
 *   }
 * ) {
 *   // ......
 * }
 * ```
 * 很明显就能看出这样写很不优雅，如果是想 rxjava 一样优雅就好了
 *
 * 那该怎么实现呢？
 *
 * 思路很简单，就是让函数返回一个对象，借助 launch 会不是立马处理的特性，我们可以在返回的对象中设置异常的捕获
 *
 * ## 有三种情况：
 * ### 一、链式调用并处理异常
 * ```
 * launchCatch {
 *   // 网络请求
 * }.catch {
 *   // 处理错误
 * }
 * ```
 * 上面这样写可以直接捕获网络请求中的异常
 *
 * ###############################
 *
 * ### 二、直接调用不捕获异常
 * ```
 * launchCatch {
 *   // 网络请求
 * }
 * ```
 * 这样写就会直接把异常往上抛出，不由自己处理
 *
 * ###############################
 *
 * ### 三、调用与异常捕获分开
 * ```
 * val catcher = launchCatch {
 *   // 网络请求
 * }
 * ... // 中间间隔一些代码
 * catcher.catch {
 *   // 间隔一些代码后再捕获异常
 * }
 * ```
 * 这样写会出现两种情况：
 * - 1、在设置 catch 前协程体已经完成了，这个时候异常捕获会失效，可以使用 tryCatch 方法得到是否成功的返回值，
 *   但此时如果协程体内出现异常，将直接往上抛出
 * - 2、在设置 catch 前协程体未完成，这时可以正常捕获到异常，与 一 情况无差别
 *
 * ```
 */
fun LifecycleOwner.launchCatch(action: suspend () -> Unit): LaunchCatcher {
  // 这里使用匿名子类来实现调用 protected 方法
  val catcher = object : LaunchCatcher() {
    fun tryEmitInternal(e: Exception) = tryEmit(e)
    fun finishInternal() = finish()
  }
  lifecycleScope.launch {
    try {
      action.invoke()
    } catch (e: Exception) {
      catcher.tryEmitInternal(e)
    }
    catcher.finishInternal()
  }
  return catcher
}

/**
 * [launchCatch] 异常的捕获者
 */
open class LaunchCatcher {
  
  private var mCatchAction: ((Exception) -> Unit)? = null
  private var mIsFinish = false
  
  /**
   * 捕获异常
   *
   * 因为 launch 是会延迟调用的，与 Handler 类似，所以只要立马设置异常，就一定能设置成功
   */
  fun catch(action: (Exception) -> Unit) {
    mCatchAction = action
  }
  
  /**
   * 尝试捕捉异常，如果捕捉失败会返回 false
   *
   * 可以覆盖之前设置的异常捕捉
   */
  fun tryCatch(action: (Exception) -> Unit): Boolean {
    if (!isFinish()) {
      mCatchAction = action
      return true
    }
    return false
  }
  
  /**
   * 返回是否之前设置了异常
   */
  fun hasCatch(): Boolean {
    return mCatchAction != null
  }
  
  /**
   * 返回是否完成，存在两种情况，一种是协程完成了，另一种是协程出错抛出了异常也算完成
   */
  fun isFinish(): Boolean {
    return mIsFinish
  }
  
  /**
   * 尝试捕捉异常，如果 [mCatchAction] 不为空，就捕捉，为空，就抛出
   *
   * 这里使用 protected 有它的意义，
   * 因为使用 public 时你链式调用它会把这个函数给主动提示出来，
   * 但该函数是内部使用的函数，并不建议暴露给其他人使用，
   * 所以使用 protected，然后在子类中调用
   */
  protected fun tryEmit(e: Exception) {
    val action = mCatchAction
    if (action == null) {
      throw e
    } else {
      action.invoke(e)
    }
  }
  
  /**
   * 协程完成的时候调用
   *
   * 使用 protected 原理与 [tryEmit] 原理相同
   */
  protected fun finish() {
    mIsFinish = true
  }
}
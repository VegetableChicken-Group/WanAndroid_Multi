package com.ndhzs.lib.common.extensions

import com.ndhzs.lib.common.network.ApiException
import com.ndhzs.lib.common.network.ApiStatus
import com.ndhzs.lib.common.network.ApiWrapper
import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.disposables.Disposable

/**
 * ```
 * 示例代码：
 * ApiService.INSTANCE.getXXX()
 *     .subscribeOn(Schedulers.io())  // 线程切换
 *     .observeOn(AndroidSchedulers.mainThread())
 *     .mapOrCatchApiException {      // 当 errorCode 的值不为成功时抛错，并处理错误
 *         // 处理 ApiException 错误
 *     }
 *     .safeSubscribeBy {             // 如果是网络连接错误，则这里会默认处理
 *         // 成功的时候
 *     }
 *     .lifeCycle() // ViewModel 中带有的自动回收，建议加上，但 ViewModel 对于 safeSubscribeBy 已经默认添加
 * ```
 *
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2022/5/30 10:12
 */

fun <T: ApiStatus> Single<T>.throwApiExceptionIfFail(): Single<T> {
  return doOnSuccess { it.throwApiExceptionIfFail() }
}

fun <T: ApiStatus> Maybe<T>.throwApiExceptionIfFail(): Maybe<T> {
  return doOnSuccess { it.throwApiExceptionIfFail() }
}

fun <T: ApiStatus> Observable<T>.throwApiExceptionIfFail(): Observable<T> {
  return doOnNext { it.throwApiExceptionIfFail() }
}

fun <T: ApiStatus> Flowable<T>.throwApiExceptionIfFail(): Flowable<T> {
  return doOnNext { it.throwApiExceptionIfFail() }
}

fun <E: Any, T: ApiWrapper<E>> Single<T>.mapOrThrowApiException(): Single<E> {
  return throwApiExceptionIfFail()
    .map { it.data }
}

fun <E: Any, T: ApiWrapper<E>> Maybe<T>.mapOrThrowApiException(): Maybe<E> {
  return throwApiExceptionIfFail()
    .map { it.data }
}

fun <E: Any, T: ApiWrapper<E>> Observable<T>.mapOrThrowApiException(): Observable<E> {
  return throwApiExceptionIfFail()
    .map { it.data }
}

fun <E: Any, T: ApiWrapper<E>> Flowable<T>.mapOrThrowApiException(): Flowable<E> {
  return throwApiExceptionIfFail()
    .map { it.data }
}

fun <T: ApiStatus> Single<T>.catchApiException(
  func: (ApiException) -> Unit
): Single<T> {
  return throwApiExceptionIfFail()
    .doOnError {
      if (it is ApiException) func.invoke(it)
    }
}

fun <T: ApiStatus> Maybe<T>.catchApiException(
  func: (ApiException) -> Unit
): Maybe<T> {
  return throwApiExceptionIfFail()
    .doOnError {
      if (it is ApiException) func.invoke(it)
    }
}

fun <T: ApiStatus> Observable<T>.catchApiException(
  func: (ApiException) -> Unit
): Observable<T> {
  return throwApiExceptionIfFail()
    .doOnError {
      if (it is ApiException) func.invoke(it)
    }
}

fun <T: ApiStatus> Flowable<T>.catchApiException(
  func: (ApiException) -> Unit
): Flowable<T> {
  return throwApiExceptionIfFail()
    .doOnError {
      if (it is ApiException) func.invoke(it)
    }
}

fun <E: Any, T: ApiWrapper<E>> Single<T>.mapOrCatchApiException(
  func: (ApiException) -> Unit
): Single<E> {
  return catchApiException(func)
    .map { it.data }
}

fun <E: Any, T: ApiWrapper<E>> Maybe<T>.mapOrCatchApiException(
  func: (ApiException) -> Unit
): Maybe<E> {
  return catchApiException(func)
    .map { it.data }
}

fun <E: Any, T: ApiWrapper<E>> Observable<T>.mapOrCatchApiException(
  func: (ApiException) -> Unit
): Observable<E> {
  return catchApiException(func)
    .map { it.data }
}

fun <E: Any, T: ApiWrapper<E>> Flowable<T>.mapOrCatchApiException(
  func: (ApiException) -> Unit
): Flowable<E> {
  return catchApiException(func)
    .map { it.data }
}

fun <T: Any> Single<T>.safeSubscribeBy(
  onError: (Throwable) -> Unit = {},
  onSuccess: (T) -> Unit = {}
): Disposable = subscribe(onSuccess, onError)

fun <T: Any> Maybe<T>.safeSubscribeBy(
  onError: (Throwable) -> Unit = {},
  onComplete: () -> Unit = {},
  onSuccess: (T) -> Unit = {}
): Disposable = subscribe(onSuccess, onError, onComplete)

fun <T : Any> Observable<T>.safeSubscribeBy(
  onError: (Throwable) -> Unit = {},
  onComplete: () -> Unit = {},
  onNext: (T) -> Unit = {}
): Disposable = subscribe(onNext, onError, onComplete)

fun <T: Any> Flowable<T>.safeSubscribeBy(
  onError: (Throwable) -> Unit = {},
  onComplete: () -> Unit = {},
  onNext: (T) -> Unit = {}
): Disposable = subscribe(onNext, onError, onComplete)

fun Completable.safeSubscribeBy(
  onError: (Throwable) -> Unit = {},
  onComplete: () -> Unit = {},
): Disposable = subscribe(onComplete, onError)

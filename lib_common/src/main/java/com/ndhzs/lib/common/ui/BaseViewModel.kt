package com.ndhzs.lib.common.ui

import android.content.Context
import androidx.annotation.CallSuper
import androidx.lifecycle.*
import com.ndhzs.lib.common.BaseApp
import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
  
  val appContext: Context
    get() = BaseApp.appContext
  
  private val mDisposableList = mutableListOf<Disposable>()
  
  protected fun Disposable.lifecycle(): Disposable {
    mDisposableList.add(this)
    return this
  }
  
  @CallSuper
  override fun onCleared() {
    super.onCleared()
    mDisposableList
      .filter { !it.isDisposed }
      .forEach { it.dispose() }
    mDisposableList.clear()
  }
  
  /**
   * 收集 Flow 并开启协程
   */
  protected fun <T> Flow<T>.collectLaunch(action: suspend (value: T) -> Unit) {
    viewModelScope.launch {
      collect{ action.invoke(it) }
    }
  }
  
  protected fun <T : Any> Single<T>.safeSubscribeBy(
    onError: (Throwable) -> Unit = {},
    onSuccess: (T) -> Unit = {}
  ): Disposable = subscribe(onSuccess, onError).lifecycle()
  
  protected fun <T : Any> Maybe<T>.safeSubscribeBy(
    onError: (Throwable) -> Unit = {},
    onSuccess: (T) -> Unit = {}
  ): Disposable = subscribe(onSuccess, onError).lifecycle()
  
  protected fun <T : Any> Observable<T>.safeSubscribeBy(
    onError: (Throwable) -> Unit = {},
    onComplete: () -> Unit = {},
    onNext: (T) -> Unit = {}
  ): Disposable = subscribe(onNext, onError, onComplete).lifecycle()
  
  protected fun <T : Any> Flowable<T>.safeSubscribeBy(
    onError: (Throwable) -> Unit = {},
    onComplete: () -> Unit = {},
    onNext: (T) -> Unit = {}
  ): Disposable = subscribe(onNext, onError, onComplete).lifecycle()
  
  protected fun Completable.safeSubscribeBy(
    onError: (Throwable) -> Unit = {},
    onComplete: () -> Unit = {}
  ): Disposable = subscribe(onComplete, onError).lifecycle()
}
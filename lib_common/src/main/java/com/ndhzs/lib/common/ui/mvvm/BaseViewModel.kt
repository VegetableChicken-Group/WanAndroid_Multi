package com.ndhzs.lib.common.ui.mvvm

import android.content.Context
import androidx.annotation.CallSuper
import androidx.lifecycle.*
import com.ndhzs.lib.common.BaseApp
import com.ndhzs.lib.common.extensions.RxjavaLifecycle
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel(), RxjavaLifecycle {
  
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
   * 开启协程并收集 Flow
   */
  protected fun <T> Flow<T>.collectLaunch(action: suspend (value: T) -> Unit) {
    viewModelScope.launch {
      collect{ action.invoke(it) }
    }
  }
  
  /**
   * 实现 [RxjavaLifecycle] 的方向，用于带有生命周期的调用
   */
  override fun onAddRxjava(disposable: Disposable) {
    mDisposableList.add(disposable)
  }
}
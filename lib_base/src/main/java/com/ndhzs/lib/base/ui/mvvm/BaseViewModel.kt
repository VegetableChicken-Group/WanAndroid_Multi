package com.ndhzs.lib.base.ui.mvvm

import android.content.Context
import androidx.annotation.CallSuper
import androidx.lifecycle.*
import com.ndhzs.lib.base.BaseApp
import com.ndhzs.lib.utils.extensions.RxjavaLifecycle
import com.ndhzs.lib.utils.extensions.ToastUtils
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel(), RxjavaLifecycle, ToastUtils {
  
  val appContext: Context
    get() = BaseApp.baseApp
  
  private val mDisposableList = mutableListOf<Disposable>()
  
  @Deprecated("ViewModel 中建议使用已拥有生命周期的 safeSubscribeBy() 方法，lifecycle() 方法将不再使用")
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
   * 实现 [RxjavaLifecycle] 的方法，用于带有生命周期的调用
   */
  override fun onAddRxjava(disposable: Disposable) {
    mDisposableList.add(disposable)
  }
}
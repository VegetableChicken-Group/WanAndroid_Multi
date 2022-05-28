package com.ndhzs.lib.common.ui.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ndhzs.lib.common.ui.BaseActivity
import com.ndhzs.lib.common.utils.GenericityUtils.getGenericClassFromSuperClass

abstract class BaseVmActivity<VM : ViewModel>(
  /**
   * 是否锁定竖屏
   */
  isPortraitScreen: Boolean = true,
  
  /**
   * 是否沉浸式状态栏
   */
  isCancelStatusBar: Boolean = true,
) : BaseActivity(isPortraitScreen, isCancelStatusBar) {
  
  @Suppress("UNCHECKED_CAST")
  protected val viewModel by lazy(LazyThreadSafetyMode.NONE) {
    val factory = getViewModelFactory()
    if (factory == null) {
      ViewModelProvider(this)[getGenericClassFromSuperClass(javaClass)] as VM
    } else {
      ViewModelProvider(this, factory)[getGenericClassFromSuperClass(javaClass)] as VM
    }
  }
  
  protected open fun getViewModelFactory(): ViewModelProvider.Factory? = null
  
  protected fun <T> LiveData<T>.observe(observer: Observer<T>) {
    observe(this@BaseVmActivity, observer)
  }
}
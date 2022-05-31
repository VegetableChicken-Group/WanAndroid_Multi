package com.ndhzs.lib.common.ui.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ndhzs.lib.common.ui.BaseActivity
import com.ndhzs.lib.common.utils.GenericityUtils.getGenericClassFromSuperClass

abstract class BaseVmActivity<VM : ViewModel>(
  isPortraitScreen: Boolean = true, // 作用请查看父类
  isCancelStatusBar: Boolean = true, // 作用请查看父类
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
}
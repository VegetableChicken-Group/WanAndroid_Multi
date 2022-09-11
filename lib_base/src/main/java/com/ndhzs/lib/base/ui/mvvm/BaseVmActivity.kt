package com.ndhzs.lib.base.ui.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ndhzs.lib.base.ui.BaseActivity
import com.ndhzs.lib.utils.utils.GenericityUtils.getGenericClassFromSuperClass

abstract class BaseVmActivity<VM : ViewModel> : BaseActivity() {
  
  @Suppress("UNCHECKED_CAST")
  protected val viewModel by lazy(LazyThreadSafetyMode.NONE) {
    val factory = getViewModelFactory()
    if (factory == null) {
      ViewModelProvider(this)[getGenericClassFromSuperClass(javaClass)] as VM
    } else {
      ViewModelProvider(this, factory)[getGenericClassFromSuperClass(javaClass)] as VM
    }
  }
  
  /**
   * 这个写在这里是因为有些参数需要通过 Intent 来拿
   */
  protected open fun getViewModelFactory(): ViewModelProvider.Factory? = null
}
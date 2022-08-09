package com.ndhzs.lib.base.ui.mvvm

import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ndhzs.lib.base.ui.BaseFragment
import com.ndhzs.lib.utils.utils.GenericityUtils.getGenericClassFromSuperClass

abstract class BaseVmFragment<VM : ViewModel> : BaseFragment {
  
  constructor() : super()
  constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)
  
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
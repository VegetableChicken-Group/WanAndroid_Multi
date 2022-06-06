package com.ndhzs.lib.common.ui.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.ndhzs.lib.common.ui.BaseBindActivity
import com.ndhzs.lib.common.utils.GenericityUtils.getGenericClassFromSuperClass

/**
 * .....
 * @author 985892345
 * @email 2767465918@qq.com
 * @data 2021/6/2
 */
abstract class BaseVmBindActivity<VM : ViewModel, VB : ViewBinding>(
  isPortraitScreen: Boolean = true, // 作用请查看父类
  isCancelStatusBar: Boolean = true, // 作用请查看父类
) : BaseBindActivity<VB>(isPortraitScreen, isCancelStatusBar) {
  
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
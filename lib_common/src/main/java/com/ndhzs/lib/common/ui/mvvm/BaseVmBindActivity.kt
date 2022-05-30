package com.ndhzs.lib.common.ui.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.ndhzs.lib.common.extensions.lazyUnlock
import com.ndhzs.lib.common.utils.GenericityUtils.getGenericClassFromSuperClass

/**
 * .....
 * @author 985892345
 * @email 2767465918@qq.com
 * @data 2021/6/2
 */
abstract class BaseVmBindActivity<VM : ViewModel, VB : ViewBinding>(
  /**
   * 是否锁定竖屏
   */
  isPortraitScreen: Boolean = true,
  
  /**
   * 是否沉浸式状态栏
   */
  isCancelStatusBar: Boolean = true,
  
  ) : BaseVmActivity<VM>(isPortraitScreen, isCancelStatusBar) {
  
  /**
   * 用于在调用 [setContentView] 之前的方法, 可用于设置一些主题或窗口的东西, 放这里不会报错
   */
  protected open fun onSetContentViewBefore() {}
  
  @Suppress("UNCHECKED_CAST")
  protected val binding: VB by lazyUnlock {
    val method = getGenericClassFromSuperClass<VB, ViewBinding>(javaClass).getMethod(
      "inflate",
      LayoutInflater::class.java
    )
    val binding = method.invoke(null, layoutInflater) as VB
    binding
  }
  
  @CallSuper
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    onSetContentViewBefore()
    setContentView(binding.root)
  }
  
  @Deprecated(
    "打个标记，因为使用了 ViewBinding，防止你忘记删除这个",
    level = DeprecationLevel.ERROR, replaceWith = ReplaceWith("")
  )
  override fun setContentView(layoutResID: Int) {
    super.setContentView(layoutResID)
  }
}
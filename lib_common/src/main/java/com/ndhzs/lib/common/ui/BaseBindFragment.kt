package com.ndhzs.lib.common.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.ndhzs.lib.common.utils.GenericityUtils.getGenericClassFromSuperClass

/**
 * .....
 * @author 985892345
 * @email 2767465918@qq.com
 * @data 2021/6/2
 */
abstract class BaseBindFragment<VB : ViewBinding> : BaseFragment() {
  
  abstract override fun onViewCreated(view: View, savedInstanceState: Bundle?)
  
  protected lateinit var binding: VB
    private set
  
  @Suppress("UNCHECKED_CAST")
  @Deprecated(
    "不要重写该方法，请使用 onViewCreated() 代替",
    ReplaceWith("onViewCreated(view, savedInstanceState)"),
    DeprecationLevel.HIDDEN
  )
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val method = getGenericClassFromSuperClass<VB, ViewBinding>(javaClass).getMethod(
      "inflate",
      LayoutInflater::class.java,
      ViewGroup::class.java,
      Boolean::class.java
    )
    binding = method.invoke(null, inflater, container, false) as VB
    onCreateViewBefore(container, savedInstanceState)
    return binding.root
  }
  
  /**
   * 在 [onCreateView] 中返回 View 前回调
   */
  open fun onCreateViewBefore(
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ) {
  }
}
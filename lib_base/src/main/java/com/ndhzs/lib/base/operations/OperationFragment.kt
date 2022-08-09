package com.ndhzs.lib.base.operations

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.ndhzs.lib.base.ui.BaseUi
import com.ndhzs.lib.utils.extensions.RxjavaLifecycle

/**
 *
 * 业务层的 Fragment
 *
 * 主要用于书写与业务相耦合的代码，比如需要使用到 api 模块时
 *
 * @author 985892345 (Guo Xiangrui)
 * @email guo985892345@foxmail.com
 * @date 2022/8/8 21:01
 */
abstract class OperationFragment : Fragment, BaseUi, RxjavaLifecycle {
  constructor() : super()
  constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)
  
}
package com.ndhzs.module.test.page

import com.ndhzs.lib.base.ui.BaseFragment
import com.ndhzs.module.test.R

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email guo985892345@foxmail.com
 * @date 2022/7/25 18:42
 */
class TestFragment : BaseFragment(R.layout.test_fragment_show) {
  /*
  * 你继承可以这样写
  * ```
  * class TestFragment : BaseFragment(R.layout.test_fragment_show)
  * ```
  * 这样写时就不用再重写 onCreatedView 了，而是 Fragment 内部自动帮你加载
  * */
}
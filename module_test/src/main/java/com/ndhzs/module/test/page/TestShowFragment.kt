package com.ndhzs.module.test.page

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.ndhzs.api.test.TEST_SHOW
import com.ndhzs.lib.base.ui.BaseFragment
import com.ndhzs.module.test.R

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2022/5/27 21:40
 */
@Route(path = TEST_SHOW)
class TestShowFragment : BaseFragment(R.layout.test_fragment_show) {
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
  }
}
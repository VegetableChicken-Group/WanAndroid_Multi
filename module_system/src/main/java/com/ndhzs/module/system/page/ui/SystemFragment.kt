package com.ndhzs.module.system.page.ui

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.ndhzs.lib.common.ui.mvvm.BaseVmBindFragment
import com.ndhzs.module.system.adapter.SystemFragmentStateAdapter
import com.ndhzs.module.system.databinding.FragmentSystemBinding
import com.ndhzs.module.system.page.viewmodel.SystemViewModel

/**
 * ...
 * @author 1446157077 (Watermelon02)
 * @email 1446157077@qq.com
 * @date 2022/5/29 18:11
 */
@Route(path = "/system/system")
class SystemFragment : BaseVmBindFragment<SystemViewModel, FragmentSystemBinding>() {
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
  }
}
package com.ndhzs.module.system.page.ui

import android.os.Bundle
import android.view.View
import com.ndhzs.lib.common.ui.mvvm.BaseVmBindFragment
import com.ndhzs.module.system.databinding.FragmentSystemBinding
import com.ndhzs.module.system.page.viewmodel.SystemViewModel

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2022/5/29 18:11
 */
class SystemFragment : BaseVmBindFragment<SystemViewModel, FragmentSystemBinding>() {
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    
  }
}
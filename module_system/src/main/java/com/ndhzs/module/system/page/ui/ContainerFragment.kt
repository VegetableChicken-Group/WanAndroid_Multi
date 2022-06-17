package com.ndhzs.module.system.page.ui

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.tabs.TabLayoutMediator
import com.ndhzs.lib.common.config.SYSTEM_CONTAINER
import com.ndhzs.lib.common.ui.mvvm.BaseVmBindFragment
import com.ndhzs.module.system.adapter.ContainerFragmentAdapter
import com.ndhzs.module.system.databinding.FragmentContainerBinding
import com.ndhzs.module.system.page.viewmodel.SystemArticleViewModel
import com.ndhzs.module.system.page.viewmodel.SystemViewModel

/**
 * description ： TODO:类的作用
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/5/30 16:08
 */
@Route(path = SYSTEM_CONTAINER)
class ContainerFragment : BaseVmBindFragment<SystemViewModel, FragmentContainerBinding>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = ContainerFragmentAdapter(this)
        binding.containerVp.adapter = adapter
        TabLayoutMediator(binding.containerTab, binding.containerVp) { tab, position ->
            when (position) {
                0 -> tab.text = "体系"
                1 -> tab.text = "导航"
            }
        }.attach()
    }
}
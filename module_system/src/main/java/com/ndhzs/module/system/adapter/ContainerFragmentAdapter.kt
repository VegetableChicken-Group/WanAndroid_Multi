package com.ndhzs.module.system.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ndhzs.lib.common.config.SYSTEM_SYSTEM
import com.ndhzs.lib.common.service.ServiceManager
import com.ndhzs.module.system.page.ui.NavigationFragment
import com.ndhzs.module.system.page.viewmodel.NavigationViewModel

/**
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/5/30 10:43
 */
class ContainerFragmentAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {
    private val navigationViewModel = NavigationViewModel()
    private val systemFragment = ServiceManager.fragment(SYSTEM_SYSTEM)
    private val navigationFragment = NavigationFragment(navigationViewModel)

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        //预加载navigation界面的数据
        navigationViewModel.getNavigation {  }
        return when (position) {
            0 -> systemFragment
            1 -> navigationFragment
            else -> systemFragment
        }
    }

}
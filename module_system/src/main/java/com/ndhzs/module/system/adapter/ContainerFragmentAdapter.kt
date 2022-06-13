package com.ndhzs.module.system.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ndhzs.lib.common.service.ServiceManager

/**
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/5/30 10:43
 */
class ContainerFragmentAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {
    private val systemFragment = ServiceManager.fragment("/system/system")
    private val navigationFragment = ServiceManager.fragment("/system/navigation")

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> systemFragment
            1 -> navigationFragment
            else -> systemFragment
        }
    }

}
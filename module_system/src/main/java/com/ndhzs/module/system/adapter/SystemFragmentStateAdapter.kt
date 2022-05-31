package com.ndhzs.module.system.adapter

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ndhzs.lib.common.service.ServiceManager

/**
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/5/30 10:43
 */
class SystemFragmentStateAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                Log.d("testTag", "createFragment: ")
                ServiceManager.fragment("/system/system")
            }
            1 -> ServiceManager.fragment("/system/navigation")
            else -> ServiceManager.fragment("/system/system")
        }
    }

}
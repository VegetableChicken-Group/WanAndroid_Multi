package com.ndhzs.module.project.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 *
 * @ProjectName:    WanAndroid_Multi
 * @Package:        com.ndhzs.module.project.adapter
 * @ClassName:      ProjectFragmentStateAdapter
 * @Author:         Yan
 * @CreateDate:     2022年06月04日 19:29:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    项目ViewPager 的适配器
 */
class ProjectFragmentStateAdapter(fragmentManger : FragmentManager,lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManger,lifecycle) {

    private val mFragments = mutableListOf<Fragment>()

    fun addFragment(fragment : Fragment){
        mFragments.add(fragment)
    }

    override fun getItemCount(): Int {
        return mFragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return mFragments[position]
    }



}
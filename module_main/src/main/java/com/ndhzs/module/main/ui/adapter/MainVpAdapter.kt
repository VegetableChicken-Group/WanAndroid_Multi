package com.ndhzs.module.main.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * com.ndhzs.module.main.ui.adapter.MainVpAdapter
 * WanAndroid_Multi
 *
 * @author 寒雨
 * @since 2022/5/30 22:42
 **/
class MainVpAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val initializer: (index: Int) -> Fragment
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        return initializer(position)
    }

}
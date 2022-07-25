package com.ndhzs.lib.common.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email guo985892345@foxmail.com
 * @date 2022/7/25 18:22
 */
class FragmentVpAdapter private constructor(
  fragmentManager: FragmentManager,
  lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
  
  constructor(activity: FragmentActivity) : this(activity.supportFragmentManager, activity.lifecycle)
  constructor(fragment: Fragment) : this(fragment.childFragmentManager, fragment.lifecycle)
  
  private val mFragments = arrayListOf<() -> Fragment>()
  
  fun add(fragment: () -> Fragment): FragmentVpAdapter {
    mFragments.add(fragment)
    return this
  }
  
  fun add(fragment: Class<out Fragment>): FragmentVpAdapter {
    // 官方源码中在恢复 Fragment 时就是调用的这个反射方法，该方法应该不是很耗性能 :)
    mFragments.add { fragment.newInstance() }
    return this
  }
  
  override fun getItemCount(): Int = mFragments.size
  
  override fun createFragment(position: Int): Fragment = mFragments[position].invoke()
}
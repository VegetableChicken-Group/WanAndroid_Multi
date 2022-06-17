package com.ndhzs.module.system.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ndhzs.module.system.page.ui.SystemArticleFragment

/**
 * description ： TODO:类的作用
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/6/14 11:48
 */
class SystemChapterAdapter(private val chapterId :ArrayList<Int>, fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = chapterId.size

    override fun createFragment(position: Int): Fragment {
        return SystemArticleFragment(chapterId[position])
    }
}
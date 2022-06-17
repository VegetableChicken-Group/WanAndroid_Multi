package com.ndhzs.module.system.page.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.ndhzs.lib.common.config.SYSTEM_NAVIGATION
import com.ndhzs.lib.common.ui.BaseBindFragment
import com.ndhzs.lib.common.ui.mvvm.BaseVmBindFragment
import com.ndhzs.module.system.adapter.NavigationChapterAdapter
import com.ndhzs.module.system.adapter.NavigationTabAdapter
import com.ndhzs.module.system.databinding.FragmentNavigationBinding
import com.ndhzs.module.system.page.viewmodel.NavigationViewModel

/**
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/5/30 10:43
 */
@Route(path = SYSTEM_NAVIGATION)
class NavigationFragment(val viewModel: NavigationViewModel) : BaseBindFragment<FragmentNavigationBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.navigationRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.navigationTab.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        //从ViewModel获取Navigation界面展示的数据，传入左右两边的Rv
        viewModel.getNavigation {
            binding.navigationRv.adapter = NavigationChapterAdapter(it)
            binding.navigationTab.adapter = NavigationTabAdapter(it, binding.navigationRv)
            setNavigationRvListener()
            setNavigationTabListener()
        }
    }

    private fun setNavigationTabListener() {
        binding.navigationTab.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val firstTab =
                    (binding.navigationTab.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                val lastTab =
                    (binding.navigationTab.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                for (i in firstTab..lastTab) {
                    //重置所有可见的tab title的颜色
                    (binding.navigationTab.layoutManager?.getChildAt(i) as FrameLayout?)?.setBackgroundColor(
                        Color.parseColor("#F4F4F4")
                    )
                    ((binding.navigationTab.layoutManager?.getChildAt(i) as FrameLayout?)?.getChildAt(
                        0
                    ) as TextView?)?.setTextColor(Color.BLACK)
                }
                val firstChapter =
                    (binding.navigationRv.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                //将右边首个可见的对应tabTitle改变颜色
                (binding.navigationTab.layoutManager?.getChildAt(firstChapter - firstTab) as FrameLayout?)?.setBackgroundColor(
                    Color.WHITE
                )
                ((binding.navigationTab.layoutManager?.getChildAt(firstChapter - firstTab) as FrameLayout?)?.getChildAt(
                    0
                ) as TextView?)?.setTextColor(Color.parseColor("#FF03DAC5"))
            }
        })
    }

    private fun setNavigationRvListener() {
        binding.navigationRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val firstChapter =
                    (binding.navigationRv.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                binding.navigationTab.smoothScrollToPosition(firstChapter)
                val firstTab =
                    (binding.navigationTab.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                val lastTab =
                    (binding.navigationTab.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                for (i in firstTab..lastTab) {
                    //重置所有可见的tab title的颜色
                    (binding.navigationTab.layoutManager?.getChildAt(i - firstTab) as FrameLayout?)?.setBackgroundColor(
                        Color.parseColor("#F4F4F4")
                    )
                    ((binding.navigationTab.layoutManager?.getChildAt(i - firstTab) as FrameLayout?)?.getChildAt(
                        0
                    ) as TextView?)?.setTextColor(Color.BLACK)
                }
                (binding.navigationTab.layoutManager?.getChildAt(firstChapter - firstTab) as FrameLayout?)?.setBackgroundColor(
                    Color.WHITE
                )
                ((binding.navigationTab.layoutManager?.getChildAt(firstChapter - firstTab) as FrameLayout?)?.getChildAt(
                    0
                ) as TextView?)?.setTextColor(Color.parseColor("#FF03DAC5"))
                //将右边首个可见的对应tabTitle改变颜色
            }
        })
    }
}
package com.ndhzs.module.project.ui.fragment

import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.view.View
import androidx.paging.ExperimentalPagingApi
import androidx.viewpager2.widget.ViewPager2
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.ndhzs.lib.common.extensions.lazyUnlock
import com.ndhzs.lib.common.ui.mvvm.BaseVmBindFragment
import com.ndhzs.module.project.adapter.ProjectFragmentStateAdapter
import com.ndhzs.module.project.ui.viewmodel.ProjectViewModel
import com.ndhzs.module.test.R
import com.ndhzs.module.test.databinding.ProjectFragmentMainBinding

/**
 *
 * @ProjectName:    WanAndroid_Multi
 * @Package:        com.ndhzs.module.project.ui.fragment
 * @ClassName:      ProjectFragment
 * @Author:         Yan
 * @CreateDate:     2022年06月01日 22:57:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    项目的主界面 用Fragment实现UI
 */

@ExperimentalPagingApi
class ProjectFragment : BaseVmBindFragment<ProjectViewModel,ProjectFragmentMainBinding>(){


    private val tabLayout by R.id.tl_project.view<TabLayout>()
    private val viewPager by R.id.vp_project_content.view<ViewPager2>()


    private val tabTitles = mutableListOf<String>()

    private val projectFragmentStateAdapter by lazyUnlock {
        ProjectFragmentStateAdapter(requireActivity().supportFragmentManager,lifecycle)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //TabLayout 与 ViewPager2 绑定
        val tabLayoutMediator = TabLayoutMediator(tabLayout,viewPager){ tab,positon ->
            tab.text = tabTitles[positon]
        }

        //获得分类列表
        viewModel.getProjectTree()
        viewModel.projectTree.observe{ list ->

            list.forEach {
                tabTitles.add(it.name)
                projectFragmentStateAdapter.addFragment(ProjectListFragment(it.id))
            }

            viewPager.adapter = projectFragmentStateAdapter
            tabLayoutMediator.attach()
        }

        //TabLayout 监听
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{

            override fun onTabSelected(tab: TabLayout.Tab) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
            }

        })
    }
}
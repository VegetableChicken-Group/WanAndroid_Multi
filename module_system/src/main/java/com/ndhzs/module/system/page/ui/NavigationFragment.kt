package com.ndhzs.module.system.page.ui

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
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
@Route(path = "/system/navigation")
class NavigationFragment : BaseVmBindFragment<NavigationViewModel, FragmentNavigationBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.navigationRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.navigationTab.layoutManager=  LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        viewModel.getNavigation {
            binding.navigationRv.adapter = NavigationChapterAdapter(it)
            binding.navigationRv.addOnScrollListener(object :RecyclerView.OnScrollListener(){
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                }
            })
            binding.navigationTab.adapter = NavigationTabAdapter(it,binding.navigationRv.layoutManager)
        }
    }
}
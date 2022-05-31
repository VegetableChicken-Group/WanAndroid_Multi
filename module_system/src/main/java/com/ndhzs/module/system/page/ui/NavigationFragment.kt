package com.ndhzs.module.system.page.ui

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.ndhzs.lib.common.ui.mvvm.BaseVmBindFragment
import com.ndhzs.module.system.databinding.FragmentNavigationBinding
import com.ndhzs.module.system.page.viewmodel.NavigationViewModel
import com.ndhzs.module.system.page.viewmodel.SystemViewModel

/**
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/5/30 10:43
 */
@Route(path = "/system/navigation")
class NavigationFragment : BaseVmBindFragment<NavigationViewModel, FragmentNavigationBinding>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val manager = FlexboxLayoutManager(this.requireContext(), FlexDirection.ROW)
        manager.justifyContent = JustifyContent.FLEX_START
        manager.alignItems = AlignItems.CENTER
        binding.navigationRv.layoutManager = manager
    }

}
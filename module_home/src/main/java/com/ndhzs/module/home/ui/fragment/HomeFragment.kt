package com.ndhzs.module.home.ui.fragment

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.ndhzs.lib.common.config.HOME_PAGE
import com.ndhzs.lib.common.ui.mvvm.BaseVmBindFragment
import com.ndhzs.module.home.databinding.FragmentHomeBinding
import com.ndhzs.module.home.viewmodel.HomeViewModel

@Route(path = HOME_PAGE)
class HomeFragment : BaseVmBindFragment<HomeViewModel, FragmentHomeBinding>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}
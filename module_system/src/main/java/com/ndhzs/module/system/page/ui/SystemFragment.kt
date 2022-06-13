package com.ndhzs.module.system.page.ui

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.ndhzs.lib.common.ui.mvvm.BaseVmBindFragment
import com.ndhzs.module.system.adapter.SystemAdapter
import com.ndhzs.module.system.databinding.FragmentSystemBinding
import com.ndhzs.module.system.page.viewmodel.SystemViewModel

/**
 * ...
 * @author 1446157077 (Watermelon02)
 * @email 1446157077@qq.com
 * @date 2022/5/29 18:11
 */
@Route(path = "/system/system")
class SystemFragment : BaseVmBindFragment<SystemViewModel, FragmentSystemBinding>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.systemRv.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        viewModel.querySystem {
            binding.systemRv.adapter = SystemAdapter(it,requireActivity())
        }
    }
}
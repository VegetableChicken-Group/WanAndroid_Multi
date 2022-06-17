package com.ndhzs.module.system.page.ui

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.ndhzs.lib.common.R
import com.ndhzs.lib.common.config.SYSTEM_SYSTEM
import com.ndhzs.lib.common.ui.mvvm.BaseVmBindFragment
import com.ndhzs.module.system.adapter.SystemAdapter
import com.ndhzs.module.system.databinding.FragmentSystemBinding
import com.ndhzs.module.system.page.viewmodel.SystemViewModel
import okhttp3.internal.wait
import java.util.concurrent.locks.Lock

/**
 * ...
 * @author 1446157077 (Watermelon02)
 * @email 1446157077@qq.com
 * @date 2022/5/29 18:11
 */
@Route(path = SYSTEM_SYSTEM)
class SystemFragment : BaseVmBindFragment<SystemViewModel, FragmentSystemBinding>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.systemRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        viewModel.querySystem {
            binding.systemRv.adapter = SystemAdapter(it)
        }
        initRefreshLayout()
    }

    private fun initRefreshLayout() {
        binding.systemRefresh.setColorSchemeResources(
            R.color.colorPrimary,
            R.color.colorPrimaryDark,
            R.color.colorAccent
        )
        binding.systemRefresh.setOnRefreshListener {
            viewModel.querySystem { systemData ->
                binding.systemRv.adapter?.let {
                    (it as SystemAdapter?)?.system = systemData
                    binding.systemRefresh.isRefreshing = false
                }
            }
        }
    }
}
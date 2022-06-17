package com.ndhzs.module.system.page.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.ndhzs.lib.common.ui.BaseBindFragment
import com.ndhzs.lib.common.ui.mvvm.BaseVmBindFragment
import com.ndhzs.module.system.adapter.SystemArticleAdapter
import com.ndhzs.module.system.databinding.FragmentArticleBinding
import com.ndhzs.module.system.network.SystemArticleService
import com.ndhzs.module.system.page.viewmodel.SystemArticleViewModel
import kotlinx.coroutines.launch

/**
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/6/16 10:24
 */
class SystemArticleFragment(private val chapterId:Int): BaseVmBindFragment<SystemArticleViewModel, FragmentArticleBinding>() {
    val adapter = SystemArticleAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.articleRv.layoutManager = LinearLayoutManager(requireContext())
        binding.articleRv.adapter =adapter
        lifecycleScope.launch {
            viewModel.getSystemArticle(chapterId.toString()).collect{
                adapter.submitData(it)
            }
        }
        adapter.addLoadStateListener {
        }
    }
}
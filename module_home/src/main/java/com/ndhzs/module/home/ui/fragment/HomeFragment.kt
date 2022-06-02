package com.ndhzs.module.home.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.alibaba.android.arouter.facade.annotation.Route
import com.ndhzs.lib.common.config.HOME_PAGE
import com.ndhzs.lib.common.extensions.toast
import com.ndhzs.lib.common.ui.mvvm.BaseVmBindFragment
import com.ndhzs.module.home.databinding.FragmentHomeBinding
import com.ndhzs.module.home.ui.adapter.BannerVpAdapter
import com.ndhzs.module.home.ui.adapter.HomeRvPagingAdapter
import com.ndhzs.module.home.viewmodel.HomeViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Route(path = HOME_PAGE)
class HomeFragment : BaseVmBindFragment<HomeViewModel, FragmentHomeBinding>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = HomeRvPagingAdapter {
            vpBanner.adapter = BannerVpAdapter(it)
            vpBanner.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    val pos = position % it.size
                    val data = it[pos]
                    tvTitle.text = data.title
                }
            })
            lifecycleScope.launch {
                while (true) {
                    delay(5000)
                    if (vpBanner.scrollState == ViewPager2.SCROLL_STATE_IDLE) {
                        vpBanner.beginFakeDrag()
                        for (i in 1..40) {
                            delay(15)
                            vpBanner.fakeDragBy(-vpBanner.width.toFloat() / 40)
                        }
                        vpBanner.endFakeDrag()
                    }
                }
            }
            indicatorView.bindBannerVp(vpBanner, it.size)
        }
        binding.rvHome.apply {
            layoutManager = LinearLayoutManager(requireContext())
            this.adapter = adapter
            addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
        }
        viewModel.pagingData.collectLaunch {
            adapter.submitData(it)
        }
        adapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.Loading -> binding.srvHome.isRefreshing = true
                is LoadState.NotLoading -> binding.srvHome.isRefreshing = false
                is LoadState.Error -> "网络请求错误: ${(it.refresh as LoadState.Error).error.message}".toast()
            }
        }
        binding.srvHome.setOnRefreshListener {
            adapter.refresh()
        }
    }
}
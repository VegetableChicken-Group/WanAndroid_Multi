package com.ndhzs.module.home.ui.fragment

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.alibaba.android.arouter.facade.annotation.Route
import com.bumptech.glide.Glide
import com.ndhzs.api.web.IWebViewService
import com.ndhzs.lib.common.config.HOME_PAGE
import com.ndhzs.lib.common.extensions.toast
import com.ndhzs.lib.common.service.ServiceManager
import com.ndhzs.lib.common.ui.mvvm.BaseVmBindFragment
import com.ndhzs.module.home.R
import com.ndhzs.module.home.databinding.FragmentHomeBinding
import com.ndhzs.module.home.ui.adapter.BannerVpAdapter
import com.ndhzs.module.home.ui.adapter.HomeRvPagingAdapter
import com.ndhzs.module.home.utils.scrollToTop
import com.ndhzs.module.home.viewmodel.HomeViewModel
import com.ndhzs.module.main.IMainService
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Route(path = HOME_PAGE)
class HomeFragment : BaseVmBindFragment<HomeViewModel, FragmentHomeBinding>() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = HomeRvPagingAdapter(onBannerInit = {
            vpBanner.adapter = BannerVpAdapter(it) {
                ServiceManager.invoke(IWebViewService::class)
                    .startWebView(requireContext(), url)
            }
            // 解决滑动冲突
            (vpBanner.getChildAt(0) as RecyclerView).addOnItemTouchListener(object :
                RecyclerView.OnItemTouchListener {
                override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                    when (e.action) {
                        MotionEvent.ACTION_DOWN -> {
                            vpBanner.parent.requestDisallowInterceptTouchEvent(true)
                        }
                    }
                    return false
                }

                override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
                }

                override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
                }
            })
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
        },
            onArticleClick = {
                ServiceManager.invoke(IWebViewService::class)
                    .startWebView(requireContext(), link)
            },
            onLikeBtnClick = { iv ->
                viewModel.setCollectState(id, !collect).invokeOnCompletion {
                    if (it == null) {
                        collect = !collect
                        Glide.with(iv)
                            .load(if (collect) R.drawable.ic_like else R.drawable.ic_not_like)
                            .into(iv)
                        if (collect) {
                            iv.setColorFilter(Color.parseColor("#CDF68A8A"))
                        } else {
                            iv.clearColorFilter()
                        }
                    }
                }
            })
        binding.rvHome.apply {
            layoutManager = LinearLayoutManager(requireContext())
            layoutAnimation = LayoutAnimationController(AnimationUtils.loadAnimation(requireContext(), R.anim.home_fade_in))
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
        ServiceManager.invoke(IMainService::class)
            .apply {
                fabClickState.collectLaunch {
                    binding.rvHome.scrollToTop()
                }
                // 注册翻页时actionBar要执行的动作
                registerActionBarAction(HOME_PAGE) {
                    title = "玩Android"
                }
            }
    }
}
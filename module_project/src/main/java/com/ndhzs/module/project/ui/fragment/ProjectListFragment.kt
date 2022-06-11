package com.ndhzs.module.project.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.ndhzs.lib.common.extensions.invisible
import com.ndhzs.lib.common.extensions.toast
import com.ndhzs.lib.common.extensions.toastLong
import com.ndhzs.lib.common.extensions.visible
import com.ndhzs.lib.common.ui.mvvm.BaseVmBindFragment
import com.ndhzs.module.project.adapter.ProjectListAdapter
import com.ndhzs.module.project.adapter.ProjectLoadStateAdapter
import com.ndhzs.module.project.ui.viewmodel.ProjectViewModel
import com.ndhzs.module.test.R
import com.ndhzs.module.test.databinding.ProjectFragmentContentBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 *
 * @ProjectName:    WanAndroid_Multi
 * @Package:        com.ndhzs.module.project.ui.fragment
 * @ClassName:      ProjectListFragment
 * @Author:         Yan
 * @CreateDate:     2022年06月04日 18:51:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    项目列表的fragment
 */

@ExperimentalPagingApi
class ProjectListFragment(private val cid : Int) : BaseVmBindFragment<ProjectViewModel,ProjectFragmentContentBinding>(){

    private val recyclerView : RecyclerView by R.id.rv_project_content.view()

    private val progressBar : ProgressBar by R.id.pb_project_content.view()

    private val refreshLayout : SwipeRefreshLayout by R.id.srl_project_content.view()

    private val progressIndicator : LinearProgressIndicator by R.id.lpi_project_append.view()

    private val mAdapter = ProjectListAdapter()


    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = mAdapter.withLoadStateFooter(
            footer = ProjectLoadStateAdapter{
                mAdapter.retry()
            }
        )

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.getProjectList(cid = cid).collectLatest {
                    mAdapter.submitData(it)
                }
            }
        }

        mAdapter.addLoadStateListener {
            when(it.refresh){

                is LoadState.NotLoading -> {
                    progressBar.invisible()
                    progressIndicator.invisible()
                    recyclerView.visible()
                    refreshLayout.isRefreshing = false
                }
                is LoadState.Loading -> {
                    refreshLayout.isRefreshing = true
                    progressIndicator.visible()
                    progressBar.visible()
                    recyclerView.invisible()
                }
                is LoadState.Error -> {
                    progressBar.visibility = View.INVISIBLE
                    refreshLayout.isRefreshing = false
                    toastLong("网络出现错误...")
                }

            }
        }

        refreshLayout.setOnRefreshListener {
            recyclerView.swapAdapter(mAdapter,true)
            mAdapter.refresh()
        }
    }
}
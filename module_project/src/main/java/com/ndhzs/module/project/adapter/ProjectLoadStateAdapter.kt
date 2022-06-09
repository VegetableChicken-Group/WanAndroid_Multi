package com.ndhzs.module.project.adapter

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

/**
 *
 * @ProjectName:    WanAndroid_Multi
 * @Package:        com.ndhzs.module.project.adapter
 * @ClassName:      ProjectLoadStateAdapter
 * @Author:         Yan
 * @CreateDate:     2022年06月08日 11:58:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    与页脚有关的Adapter
 */

class ProjectLoadStateAdapter(private val retry: () -> Unit) : LoadStateAdapter<ProjectLoadStateViewHolder>() {
    override fun onBindViewHolder(holder: ProjectLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ProjectLoadStateViewHolder {
        return ProjectLoadStateViewHolder.create(parent, retry)
    }
}
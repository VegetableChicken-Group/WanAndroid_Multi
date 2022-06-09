package com.ndhzs.module.project.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.ndhzs.module.test.R
import com.ndhzs.module.test.databinding.ItemProjectLoadStateFooterBinding

/**
 *
 * @ProjectName:    WanAndroid_Multi
 * @Package:        com.ndhzs.module.project.adapter
 * @ClassName:      PorjectLoadStateViewHolder
 * @Author:         Yan
 * @CreateDate:     2022年06月08日 11:41:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    与页脚加载状态有关的ViewHolder
 */
class ProjectLoadStateViewHolder(
    private val binding: ItemProjectLoadStateFooterBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.retryButton.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.errorMsg.text = loadState.error.localizedMessage
        }
        binding.progressBar.isVisible = loadState is LoadState.Loading
        binding.retryButton.isVisible = loadState is LoadState.Error
        binding.errorMsg.isVisible = loadState is LoadState.Error
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): ProjectLoadStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_project_load_state_footer, parent, false)
            val binding = ItemProjectLoadStateFooterBinding.bind(view)
            return ProjectLoadStateViewHolder(binding, retry)
        }
    }
}
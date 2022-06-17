package com.ndhzs.module.system.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ndhzs.lib.common.extensions.appContext
import com.ndhzs.module.system.bean.SystemArticle
import com.ndhzs.module.system.databinding.ItemArticleBinding

/**
 * description ： TODO:类的作用
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/6/16 10:27
 */
class SystemArticleAdapter :
    PagingDataAdapter<SystemArticle.Data.Data, SystemArticleAdapter.ViewHolder>(COMPARATOR) {
    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<SystemArticle.Data.Data>() {
            override fun areItemsTheSame(
                oldItem: SystemArticle.Data.Data,
                newItem: SystemArticle.Data.Data
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: SystemArticle.Data.Data,
                newItem: SystemArticle.Data.Data
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ViewHolder(val binding: ItemArticleBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemArticleBinding.inflate(LayoutInflater.from(appContext)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = getItem(position)
        holder.binding.itemArticleTitle.text = article?.title
    }
}
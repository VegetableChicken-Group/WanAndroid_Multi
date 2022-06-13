package com.ndhzs.module.system.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ndhzs.api.web.IWebViewService
import com.ndhzs.lib.common.extensions.appContext
import com.ndhzs.lib.common.service.ServiceManager
import com.ndhzs.module.system.bean.Navigation
import com.ndhzs.module.system.databinding.ItemNavigationArticleBinding

/**
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/5/31 18:19
 */
class NavigationArticleAdapter(private val articles: List<Navigation.Data.Article>) :
    RecyclerView.Adapter<NavigationArticleAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemNavigationArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.itemNavigationArticleText.text = articles[absoluteAdapterPosition].title
            binding.itemNavigationArticleText.click = {
                ServiceManager(IWebViewService::class).startWebView(
                    appContext,
                    articles[absoluteAdapterPosition].link
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNavigationArticleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return articles.size
    }
}
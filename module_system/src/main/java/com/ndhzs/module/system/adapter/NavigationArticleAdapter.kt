package com.ndhzs.module.system.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ndhzs.module.system.bean.Navigation
import com.ndhzs.module.system.databinding.ItemNavigationArticleBinding

/**
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/5/31 18:19
 */
class NavigationArticleAdapter(private val articles: List<Navigation.Data.Article>): RecyclerView.Adapter<NavigationArticleAdapter.ViewHolder>() {
    inner class ViewHolder(val binding:ItemNavigationArticleBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemNavigationArticleBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.itemNavigationArticleText.text = articles[position].title
    }

    override fun getItemCount(): Int {
        return articles.size
    }
}
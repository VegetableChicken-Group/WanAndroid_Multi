package com.ndhzs.module.system.adapter

import android.app.Activity
import android.graphics.Color
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.ndhzs.api.web.IWebViewService
import com.ndhzs.lib.common.extensions.appContext
import com.ndhzs.lib.common.service.ServiceManager
import com.ndhzs.module.system.bean.Navigation
import com.ndhzs.module.system.databinding.ItemNavigationArticleBinding
import com.ndhzs.module.system.databinding.ItemNavigationChapterBinding
import kotlin.random.Random
import kotlin.random.nextInt

/**
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/5/31 18:19
 */
class NavigationChapterAdapter(private val chapters: Navigation,private val activity:Activity) :
    RecyclerView.Adapter<NavigationChapterAdapter.ViewHolder>() {
    private val textColorList = listOf("#2E027A", "#0A3173", "#FFCC65","#000000")
    inner class ViewHolder(val binding: ItemNavigationChapterBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNavigationChapterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.itemNavigationChapterTitle.text = chapters.data[position].name
        val manager = FlexboxLayoutManager(appContext, FlexDirection.COLUMN)
        manager.alignItems = AlignItems.CENTER
        NavigationArticleAdapter(chapters.data[position].articles)
        for (article in chapters.data[position].articles) {
            val articleBinding =
                ItemNavigationArticleBinding.inflate(LayoutInflater.from(appContext), null, false)
            articleBinding.itemNavigationArticleText.text = article.title
            articleBinding.itemNavigationArticleText.setTextColor(Color.parseColor(textColorList[Random.nextInt(0..3)]))
            articleBinding.itemNavigationArticleText.click = {
                ServiceManager(IWebViewService::class).startWebView(
                    activity,
                    article.link
                )
            }
            holder.binding.itemNavigationChapterFlex.addView(articleBinding.root)
        }
    }

    override fun getItemCount(): Int {
        return chapters.data.size
    }
}
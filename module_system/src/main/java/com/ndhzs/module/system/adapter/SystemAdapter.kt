package com.ndhzs.module.system.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.launcher.ARouter
import com.ndhzs.lib.common.config.SYSTEM_SYSTEM_ARTICLE
import com.ndhzs.lib.common.extensions.appContext
import com.ndhzs.module.system.bean.SystemData
import com.ndhzs.module.system.databinding.ItemSystemChildBinding
import com.ndhzs.module.system.databinding.ItemSystemTitleBinding

/**
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/6/12 10:31
 */
class SystemAdapter(var system: SystemData) :
    RecyclerView.Adapter<SystemAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemSystemTitleBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemSystemTitleBinding.inflate(
                LayoutInflater.from(appContext),
                null,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.itemSystemTitle.text = system.data[position].name
        for (child in system.data[position].children) {
            val articleBinding =
                ItemSystemChildBinding.inflate(LayoutInflater.from(appContext), null, false)
            articleBinding.itemSystemChild.text = child.name
            holder.binding.itemSystemFlexChildren.addView(articleBinding.root)
            //在viewHolder的init中获取的absoluteAdapterPosition为-1,所以在此处设置点击监听
            val chapterName = ArrayList<String>()
            val chapterId = ArrayList<Int>()
            for (chapter in system.data[position].children) {
                chapterName.add(chapter.name)
                chapterId.add(chapter.id)
            }
            holder.binding.root.setOnClickListener {
                ARouter.getInstance().build(SYSTEM_SYSTEM_ARTICLE)
                    .withStringArrayList("chapterName", chapterName)
                    .withIntegerArrayList("chapterId", chapterId)
                    .navigation()
            }
        }
    }

    override fun getItemCount(): Int {
        return system.data.size
    }
}
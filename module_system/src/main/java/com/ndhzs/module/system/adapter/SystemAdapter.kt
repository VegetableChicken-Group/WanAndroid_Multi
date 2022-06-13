package com.ndhzs.module.system.adapter

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ndhzs.lib.common.extensions.appContext
import com.ndhzs.module.system.bean.SystemData
import com.ndhzs.module.system.databinding.ItemNavigationArticleBinding
import com.ndhzs.module.system.databinding.ItemSystemChildBinding
import com.ndhzs.module.system.databinding.ItemSystemTitleBinding

/**
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/6/12 10:31
 */
class SystemAdapter(val system: SystemData, private val activity: Activity) :
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
                ItemSystemChildBinding.inflate(LayoutInflater.from(activity), null, false)
            articleBinding.itemSystemChild.text = child.name
            holder.binding.itemSystemFlexChildren.addView(articleBinding.root)
        }
    }

    override fun getItemCount(): Int {
        return system.data.size
    }
}
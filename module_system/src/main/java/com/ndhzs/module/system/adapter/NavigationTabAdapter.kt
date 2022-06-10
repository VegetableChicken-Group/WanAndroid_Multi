package com.ndhzs.module.system.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ndhzs.module.system.bean.Navigation
import com.ndhzs.module.system.databinding.ItemNavigationTabBinding

/**
 * description ： 体系界面左边的rv的adapter
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/6/6 17:28
 */
class NavigationTabAdapter(private val chapters: Navigation,val recyclerView: RecyclerView) :
    RecyclerView.Adapter<NavigationTabAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemNavigationTabBinding) :
        RecyclerView.ViewHolder(binding.root){
            init {
                binding.root.setOnClickListener {
                    recyclerView.smoothScrollToPosition(absoluteAdapterPosition)
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNavigationTabBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.itemNavigationTabTitle.text = chapters.data[position].name
    }

    override fun getItemCount(): Int {
        return chapters.data.size
    }
}
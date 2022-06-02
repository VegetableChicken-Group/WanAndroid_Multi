package com.ndhzs.module.home.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ndhzs.lib.common.extensions.setOnSingleClickListener
import com.ndhzs.module.home.databinding.ItemBannerItemBinding
import com.ndhzs.module.home.repo.bean.BannerData

/**
 * com.ndhzs.module.home.ui.adapter.BannerVpAdapter
 * WanAndroid_Multi
 *
 * @author 寒雨
 * @since 2022/6/2 11:02
 **/
class BannerVpAdapter(private val data: List<BannerData>) : RecyclerView.Adapter<BannerVpAdapter.Holder>() {
    inner class Holder(val binding: ItemBannerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnSingleClickListener {
                val data = data[calcItemPos(bindingAdapterPosition)]
                // TODO 进入webView
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(ItemBannerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = data[calcItemPos(position)]
        holder.binding.apply {
            Glide.with(ivBanner)
                .load(data.imagePath)
                .centerCrop()
                .into(ivBanner)
        }
    }

    override fun getItemCount(): Int = Int.MAX_VALUE

    private fun calcItemPos(position: Int): Int {
        return position % data.size
    }
}
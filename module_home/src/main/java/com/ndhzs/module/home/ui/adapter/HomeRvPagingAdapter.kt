package com.ndhzs.module.home.ui.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.ndhzs.lib.common.extensions.setOnSingleClickListener
import com.ndhzs.module.home.R
import com.ndhzs.module.home.databinding.ItemArticleBinding
import com.ndhzs.module.home.databinding.ItemBannerBinding
import com.ndhzs.module.home.repo.bean.ArticleData
import com.ndhzs.module.home.repo.bean.BannerData
import com.ndhzs.module.home.repo.bean.wrapper.HomeRvItemWrapper
import com.ndhzs.module.home.utils.allTheSame
import com.ndhzs.module.home.utils.htmlDecode

/**
 * com.ndhzs.module.home.ui.adapter.HomeRvPagingAdapter
 * WanAndroid_Multi
 *
 * @author 寒雨
 * @since 2022/6/1 21:03
 **/
class HomeRvPagingAdapter(
    private val onBannerInit: ItemBannerBinding.(List<BannerData>) -> Unit
) : PagingDataAdapter<HomeRvItemWrapper, HomeRvPagingAdapter.Holder>(DiffCallback()) {

    @Suppress("UNCHECKED_CAST")
    inner class Holder(val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            if (binding is ItemArticleBinding) {
                binding.root.setOnSingleClickListener {
                    val data = getItem(bindingAdapterPosition)!!.content as ArticleData
                    // TODO 进入webView
                }
            } else if (binding is ItemBannerBinding) {
                binding.root.post {
                    val data = getItem(bindingAdapterPosition)!!.content as List<BannerData>
                    binding.onBannerInit(data)
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.binding.apply {
            val data = getItem(position)!!
            when (data.type) {
                HomeRvItemWrapper.Type.ARTICLE -> {
                    val d = data.content as ArticleData
                    this as ItemArticleBinding
                    homeRvTitle.text = d.title.htmlDecode()
                    homeRvDate.text = d.niceDate
                    tvAuthor.text = d.author.ifEmpty { d.shareUser }
                    homeRvLabel.text = d.superChapterName + "/" + d.chapterName
                    homeButtonLike.apply {
                        if (d.collect) {
                            setImageResource(R.drawable.ic_like)
                            setColorFilter(Color.parseColor("#CDF68A8A"))
                        } else {
                            setImageResource(R.drawable.ic_not_like)
                            clearColorFilter()
                        }
                    }

                }
                else -> {}
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(if (viewType == 0) {
            ItemBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        } else {
            ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        })
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position)?.type == HomeRvItemWrapper.Type.BANNER) 0 else 1
    }

    class DiffCallback : DiffUtil.ItemCallback<HomeRvItemWrapper>() {
        @Suppress("UNCHECKED_CAST")
        override fun areItemsTheSame(
            oldItem: HomeRvItemWrapper,
            newItem: HomeRvItemWrapper,
        ): Boolean {
            if (oldItem.type == newItem.type) {
                return if (oldItem.type == HomeRvItemWrapper.Type.BANNER) {
                    (oldItem.content as List<BannerData>).allTheSame(newItem.content as List<BannerData>) { item1, item2 ->
                        item1.id == item2.id
                    }
                } else {
                    (oldItem.content as ArticleData).id == (newItem.content as ArticleData).id
                }
            }
            return false
        }

        @Suppress("UNCHECKED_CAST")
        override fun areContentsTheSame(
            oldItem: HomeRvItemWrapper,
            newItem: HomeRvItemWrapper,
        ): Boolean {
            if (oldItem.type == newItem.type) {
                return if (oldItem.type == HomeRvItemWrapper.Type.BANNER) {
                    (oldItem.content as List<BannerData>).allTheSame(newItem.content as List<BannerData>) { item1, item2 ->
                        item1 == item2
                    }
                } else {
                    oldItem.content as ArticleData == newItem.content as ArticleData
                }
            }
            return false
        }

    }
}
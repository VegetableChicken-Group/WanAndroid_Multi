package com.ndhzs.module.home.repo.bean

/**
 * com.ndhzs.module.home.repo.bean.BannerData
 * WanAndroid_Multi
 *
 * @author 寒雨
 * @since 2022/6/1 21:12
 **/
data class BannerData(
    val desc: String,
    val id: Int,
    val imagePath: String,
    val isVisible: Int,
    val order: Int,
    val title: String,
    val type: Int,
    val url: String,
)
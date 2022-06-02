package com.ndhzs.module.home.repo.bean.wrapper

/**
 * com.ndhzs.module.home.repo.bean.warpper.HomeRvItemWrapper
 * WanAndroid_Multi
 *
 * @author 寒雨
 * @since 2022/6/1 21:37
 **/
data class HomeRvItemWrapper(
    val type: Type,
    val content: Any
) {
    enum class Type {
        BANNER, ARTICLE
    }
}
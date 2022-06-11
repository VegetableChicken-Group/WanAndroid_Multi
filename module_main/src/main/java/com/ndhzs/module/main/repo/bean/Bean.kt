package com.ndhzs.module.main.repo.bean

/**
 * com.ndhzs.module.main.repo.bean.Bean
 * WanAndroid_Multi
 *
 * @author 寒雨
 * @since 2022/6/3 22:36
 **/
data class UserInfoData(
    val coinInfo: CoinInfoData?,
    val userInfo: LoginData,
) {
    data class CoinInfoData(
        val coinCount: Int,
        val level: Int,
        val nickname: String,
        val rank: Int,
        val userId: Int,
        val username: String,
    )
}

data class LoginData(
    val admin: Boolean,
    val chapterTops: MutableList<String>,
    val coinCount: Int,
    val collectIds: MutableList<String>,
    val email: String,
    val icon: String,
    val id: Long,
    val nickname: String,
    val password: String,
    val token: String,
    val type: Int,
    val username: String,
)
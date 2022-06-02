package com.ndhzs.module.home.repo.api

import com.ndhzs.lib.common.network.ApiGenerator
import com.ndhzs.lib.common.network.ApiWrapper
import com.ndhzs.module.home.repo.bean.ArticlesData
import com.ndhzs.module.home.repo.bean.BannerData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * com.ndhzs.module.home.repo.api.HomeWebService
 * WanAndroid_Multi
 *
 * @author 寒雨
 * @since 2022/6/1 21:06
 **/
interface HomeWebService {
    /**
     * 获取Banner
     *
     * @return
     */
    @GET("banner/json")
    suspend fun getBanner(): ApiWrapper<List<BannerData>>

    /**
     * 获取文章
     *
     * @param page 页数
     * @param pageSize 每页的数量
     * @return data
     */
    @GET("article/list/{page}/json")
    suspend fun getArticles(
        @Path("page") page: Int,
        @Query("page_size") pageSize: Int,
    ): ApiWrapper<ArticlesData>

    companion object {
        val INSTANCE by lazy {
            ApiGenerator.getApiService(HomeWebService::class)
        }
    }
}
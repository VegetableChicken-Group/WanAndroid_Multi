package com.ndhzs.module.system.logic.net

import com.ndhzs.lib.common.network.ApiGenerator
import com.ndhzs.lib.common.network.ApiWrapper
import com.ndhzs.module.system.logic.model.Data
import com.ndhzs.module.system.logic.model.HomeArticle
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *
 * @author :WhiteNight123(Guo Xiaoqiang)
 * @email 1448375249@qq.com
 * @date 2022/6/5
 */
interface ApiService {
    @GET("/article/list/{page}/json")
    fun getHomeArticle(@Path("page") page: Int): Single<ApiWrapper<Data>>

    companion object {
        val INSTANCE by lazy {
            ApiGenerator.getApiService(ApiService::class)
        }
    }
}
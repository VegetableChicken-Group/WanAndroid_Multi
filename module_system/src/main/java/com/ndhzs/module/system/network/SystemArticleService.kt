package com.ndhzs.module.system.network

import android.util.Log
import com.ndhzs.lib.common.network.ApiGenerator
import com.ndhzs.module.system.bean.SystemArticle
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/6/13 18:58
 */
interface SystemArticleService {
    @GET(value = "/article/list/{page}/json")
    suspend fun getSystemArticle(@Path("page") page: Int, @Query("cid") cid: String,@Query("page_size")pageSize:Int): SystemArticle

    companion object {
        val INSTANCE by lazy {
            ApiGenerator.getApiService(SystemArticleService::class)
        }
    }
}
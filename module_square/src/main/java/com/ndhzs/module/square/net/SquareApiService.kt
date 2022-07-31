package com.ndhzs.module.square.net

import android.database.Observable
import com.ndhzs.lib.common.network.ApiGenerator
import com.ndhzs.lib.common.network.ApiWrapper
import com.ndhzs.module.square.bean.ItemListBean
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @ClassName SquareApiService
 * @author Silence~
 * @date 2022/7/31
 * @Description
 */
interface SquareApiService {
    companion object {
        val INSTANCE by lazy {
            ApiGenerator.getApiService(SquareApiService::class)
        }
    }

    @GET("https://wanandroid.com/user_article/list/{page}/json")
    fun getSquareItemList(@Path("page") page : Int): Observable<ApiWrapper<ItemListBean>> // 统一使用ApiWrapper包装
}
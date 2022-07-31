package com.ndhzs.module.square.net

import android.database.Observable
import com.ndhzs.lib.common.network.ApiGenerator
import com.ndhzs.lib.common.network.ApiWrapper
import com.ndhzs.module.square.bean.ItemListBean
import retrofit2.http.GET

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

    @GET("https://wanandroid.com/user_article/list/0/json")
    fun getSquareItemList(): Observable<ApiWrapper<ItemListBean>> // 统一使用 ApiWrapper 包装
}
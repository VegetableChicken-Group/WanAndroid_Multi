package com.ndhzs.module.system.network

import com.ndhzs.lib.common.network.ApiGenerator
import com.ndhzs.lib.common.network.ApiWrapper
import com.ndhzs.module.system.bean.Navigation
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

/**
 * description ： TODO:类的作用
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/5/31 17:01
 */
interface NavigationService {
    @GET("/navi/json")
    fun getNavigation(): Single<ApiWrapper<Navigation>>

    companion object {
        val INSTANCE by lazy {
            ApiGenerator.getApiService(NavigationService::class)
        }
    }
}
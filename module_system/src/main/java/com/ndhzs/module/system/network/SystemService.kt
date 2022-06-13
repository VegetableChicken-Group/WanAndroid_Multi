package com.ndhzs.module.system.network

import com.ndhzs.lib.common.network.ApiGenerator
import com.ndhzs.module.system.bean.SystemData
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

/**
 * description ： TODO:类的作用
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/6/12 10:16
 */
interface SystemService {
    @GET(value = "/tree/json")
    fun querySystem():Single<SystemData>
    companion object {
        val INSTANCE by lazy {
            ApiGenerator.getApiService(SystemService::class)
        }
    }
}
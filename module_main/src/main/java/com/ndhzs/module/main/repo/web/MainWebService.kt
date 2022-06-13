package com.ndhzs.module.main.repo.web

import com.ndhzs.lib.common.network.ApiGenerator
import com.ndhzs.lib.common.network.ApiWrapper
import com.ndhzs.module.main.repo.bean.UserInfoData
import io.reactivex.rxjava3.core.Observable

/**
 * com.ndhzs.module.main.repo.web.MainWebService
 * WanAndroid_Multi
 *
 * @author 寒雨
 * @since 2022/6/3 22:35
 **/
interface MainWebService {
    fun getInfo(): Observable<ApiWrapper<UserInfoData>>

    companion object {
        val INSTANCE by lazy { ApiGenerator.getApiService(MainWebService::class) }
    }
}
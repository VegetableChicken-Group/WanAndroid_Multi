package com.ndhzs.module.project.network

import android.database.Observable
import com.ndhzs.lib.common.extensions.lazyUnlock
import com.ndhzs.lib.common.network.ApiGenerator
import com.ndhzs.lib.common.network.ApiWrapper
import com.ndhzs.module.project.bean.*
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *
 * @ProjectName:    WanAndroid_Multi
 * @Package:        com.ndhzs.module.project.network
 * @ClassName:      ApiServiceProject
 * @Author:         Yan
 * @CreateDate:     2022年06月01日 22:34:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    项目网络请求API
 */
interface ApiServiceProject {

    companion object{

        val Instance by lazy{
            ApiGenerator.getApiService(ApiServiceProject::class)
        }

    }

    /**
     * 获得项目分类
     */
    @GET("/project/tree/json")
    fun getProjectTree() : Single<ApiWrapper<List<ProjectTree>>>


    /**
     * 获得项目列表
     */
    @GET("/project/list/1/json")
    fun getProjectList(@Query("cid") cid : Int) : Single<ApiWrapper<List<ProjectList>>>
}
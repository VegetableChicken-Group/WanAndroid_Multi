package com.ndhzs.module.project.network


import com.ndhzs.lib.common.network.ApiGenerator
import com.ndhzs.lib.common.network.ApiWrapper
import com.ndhzs.module.project.bean.*
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path
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
    fun getProjectTree() : Observable<ApiWrapper<List<ProjectTree>>>


    /**
     * 获得项目列表
     * @param path 页数
     * @param cid
     */
    @GET("/project/list/{path}/json")
    suspend fun getProjectList(
        @Path("path") path : Int,@Query("cid") cid : Int) : ApiWrapper<ProjectLists>
}
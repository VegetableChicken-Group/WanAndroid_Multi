package com.ndhzs.module.project.repository

import android.database.Observable
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ndhzs.lib.common.network.ApiWrapper
import com.ndhzs.module.project.bean.ProjectList
import com.ndhzs.module.project.network.ApiServiceProject
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.toList

/**
 *
 * @ProjectName:    WanAndroid_Multi
 * @Package:        com.ndhzs.module.project.repository
 * @ClassName:      ProjectDataSource
 * @Author:         Yan
 * @CreateDate:     2022年06月02日 14:09:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    项目 paging 数据来源
 */
class ProjectDataSource(private val projectService : ApiServiceProject,private val cid : Int) :
    PagingSource<Int, ProjectList>() {

    override fun getRefreshKey(state: PagingState<Int,ProjectList>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProjectList> {

        return try {

            val pageNum = params.key ?: 1
            val data = projectService.getProjectList(path = pageNum,cid = cid)
            val preKey = if (pageNum > 1) pageNum - 1 else null

            LoadResult.Page(data = data.data.datas, prevKey = preKey, nextKey = pageNum + 1)

        } catch (e: Exception) {
            Log.e("ProjectError",e.toString())
            LoadResult.Error(e)
        }

    }

}
package com.ndhzs.module.project.repository


import android.util.DebugUtils
import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ndhzs.lib.common.extensions.appContext
import com.ndhzs.lib.common.extensions.mapOrCatchApiException
import com.ndhzs.lib.common.extensions.toast
import com.ndhzs.lib.common.network.ApiWrapper
import com.ndhzs.module.project.bean.ProjectList
import com.ndhzs.module.project.bean.ProjectTree
import com.ndhzs.module.project.db.ProjectDataBase
import com.ndhzs.module.project.network.ApiServiceProject
import debug.DebugAPP
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.Flow

/**
 *
 * @ProjectName:    WanAndroid_Multi
 * @Package:        com.ndhzs.module.project.repository
 * @ClassName:      Project
 * @Author:         Yan
 * @CreateDate:     2022年06月02日 12:28:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    项目的Repository层
 */

@ExperimentalPagingApi
object ProjectRepository {

    private var mPath = 0

    private val service = ApiServiceProject.Instance

    private val db = ProjectDataBase.get(appContext)

    private const val PAGE_SIZE = 20

    private val config = PagingConfig(
        pageSize = PAGE_SIZE,
        prefetchDistance = 5,
        initialLoadSize = 10,
        enablePlaceholders = false,
        maxSize = PAGE_SIZE * 3
    )


    /**
     * 请求项目分类
     */
    fun getProjectTree() : Observable<ApiWrapper<List<ProjectTree>>> {
        return service.getProjectTree()
    }

    private val pagingSourceFactory = { db.projectListDao().queryLocalList(mPath) }

    /**
     * 请求项目列表
     */
    fun getProjectList(cid : Int) : Flow<PagingData<ProjectList>> {

//        return Pager(
//            config = config,
//            remoteMediator = ProjectRemoteMediator(service,db,1),
//            pagingSourceFactory = pagingSourceFactory
//        ).flow
        return Pager(
            config = config,
            pagingSourceFactory = {ProjectDataSource(ApiServiceProject.Instance,cid)}
        ).flow
    }

}
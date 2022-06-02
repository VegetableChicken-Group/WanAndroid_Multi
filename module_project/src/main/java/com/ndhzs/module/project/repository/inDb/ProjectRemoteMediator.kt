package com.ndhzs.module.project.repository.inDb

import androidx.paging.*
import com.ndhzs.module.project.bean.ProjectList
import com.ndhzs.module.project.db.ProjectDataBase
import com.ndhzs.module.project.network.ApiServiceProject
import retrofit2.HttpException
import java.io.IOException

/**
 *
 * @ProjectName:    WanAndroid_Multi
 * @Package:        com.ndhzs.module.project.repository.inDb
 * @ClassName:      ProjectRemoteMediator
 * @Author:         Yan
 * @CreateDate:     2022年06月02日 17:18:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    在page数据加载完或失效时，从网络中加载更多的数据。
 *                  使用此信号从网络中加载数据并存储在本地数据库中， PagingSource 从本地数据库加载数据并提供给界面显示
                    需要更多数据时，paging 库从remoteMediator 中的load()方法从网络中提取新数据
 */


@OptIn(ExperimentalPagingApi::class)
class ProjectRemoteMediator (
    private val api: ApiServiceProject,
    private val db: ProjectDataBase,
    private val id: Int
): RemoteMediator<Int,ProjectList>(){


    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ProjectList>
    ): MediatorResult {
        /*
        1.LoadType.REFRESH：首次访问 或者调用 PagingDataAdapter.refresh() 触发
        2.LoadType.PREPEND：在当前列表头部添加数据的时候时触发，在项目中基本很少会用到直接返回 MediatorResult.Success(endOfPaginationReached = true) ，参数 endOfPaginationReached 表示没有数据了不在加载
        3.LoadType.APPEND：加载更多时触发，这里获取下一页的 key, 如果 key 不存在，表示已经没有更多数据，直接返回 MediatorResult.Success(endOfPaginationReached = true) 不会在进行网络和数据库的访问
         */
        try {
            val pageKey : Int? = when(loadType){
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(true)
                LoadType.APPEND -> {
                        TODO()
                    }
                }


            return MediatorResult.Success(TODO())
            }catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }




    }




}
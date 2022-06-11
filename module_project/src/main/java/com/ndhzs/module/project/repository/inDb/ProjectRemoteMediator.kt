package com.ndhzs.module.project.repository.inDb

import android.util.Log
import androidx.paging.*
import androidx.room.withTransaction
import com.ndhzs.module.project.bean.ProjectList
import com.ndhzs.module.project.bean.RemoteKey
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
 * @Description:    在paging数据加载完或失效时，从网络中加载更多的数据。
 *                  使用此信号从网络中加载数据并存储在本地数据库中， PagingSource 从本地数据库加载数据并提供给界面显示
                    需要更多数据时，paging 库从remoteMediator 中的load()方法从网络中提取新数据
 */

//从index = 0 开始分页
private const val STARTING_PAGE_INDEX = 0


@ExperimentalPagingApi
class ProjectRemoteMediator (
    private val api: ApiServiceProject,
    private val db: ProjectDataBase,
    private val cid : Int
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
            Log.d("ProjectLoad",loadType.name)

            val pageKey : Int = when(loadType){

                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextKey?.minus(0) ?: STARTING_PAGE_INDEX
//                    null
                }

                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevKey = remoteKeys?.prevKey
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                    prevKey

//                    return MediatorResult.Success(endOfPaginationReached = true)
                }

                //在末尾添加数据
                LoadType.APPEND -> {
                    //使用remoteKey来获取下一个页面。
                    val remoteKey = getRemoteKeyForLastItem(state = state)
                    val nextKey = remoteKey?.nextKey
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKey != null)
                    nextKey

//                    val lastItem = state.lastItemOrNull()
//                        ?: return MediatorResult.Success(
//                            endOfPaginationReached = true
//                        )
//                    lastItem.projectId
                }
            }

            Log.d("ProjectNum",pageKey.toString())

            //从网络上请求数据
            val result = api.getProjectList(pageKey,cid).data
            val projects = result.datas
            val endOfPaginationReached = projects.isEmpty()


            db.withTransaction {

                if (loadType == LoadType.REFRESH) {
                    //清空数据
                    db.remoteKeyDao().clearRemoteKeys()
                    db.projectListDao().clearList()
                }

                val prevKey = if (pageKey == STARTING_PAGE_INDEX) null else pageKey - 1
                val nextKey = if (endOfPaginationReached) null else pageKey + 1
//
                val keys = projects.map {
                    RemoteKey(
                        projectId = it.id,
                        prevKey = prevKey,
                        nextKey = nextKey,
                    )
                }

                Log.d("Projects",keys.toString())

                db.remoteKeyDao().insertAll(remoteKey = keys)
                db.projectListDao().insertProjects(projectDataList = projects)

            }
            return MediatorResult.Success(endOfPaginationReached)
        }catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }

    }

    //获取从数据库中加载的最后一项内容的键
    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, ProjectList>): RemoteKey? {
        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let {
                db.remoteKeyDao().remoteKeysId(it.id)
            }
    }

    //获取从数据库中加载的第一个项的键
    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, ProjectList>): RemoteKey? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let {
                db.remoteKeyDao().remoteKeysId(it.id)
            }
    }

    //获取距离该位置最近的一项
    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, ProjectList>) : RemoteKey?{
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let {
                db.remoteKeyDao().remoteKeysId(it)
            }
        }
    }




}
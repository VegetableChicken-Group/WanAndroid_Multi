//package com.ndhzs.module.project.repository.inDb
//
//import androidx.paging.*
//import androidx.room.withTransaction
//import com.ndhzs.module.project.bean.ProjectList
//import com.ndhzs.module.project.bean.RemoteKey
//import com.ndhzs.module.project.db.ProjectDataBase
//import com.ndhzs.module.project.network.ApiServiceProject
//import retrofit2.HttpException
//import java.io.IOException
//
///**
// *
// * @ProjectName:    WanAndroid_Multi
// * @Package:        com.ndhzs.module.project.repository.inDb
// * @ClassName:      ProjectRemoteMediator
// * @Author:         Yan
// * @CreateDate:     2022年06月02日 17:18:00
// * @UpdateRemark:   更新说明：
// * @Version:        1.0
// * @Description:    在paging数据加载完或失效时，从网络中加载更多的数据。
// *                  使用此信号从网络中加载数据并存储在本地数据库中， PagingSource 从本地数据库加载数据并提供给界面显示
//                    需要更多数据时，paging 库从remoteMediator 中的load()方法从网络中提取新数据
// */
//
//
//@OptIn(ExperimentalPagingApi::class)
//class ProjectRemoteMediator (
//    private val api: ApiServiceProject,
//    private val db: ProjectDataBase,
//    private val path: Int,
//    private val cid : Int
//): RemoteMediator<Int,ProjectList>(){
//
//
//    override suspend fun load(
//        loadType: LoadType,
//        state: PagingState<Int, ProjectList>
//    ): MediatorResult {
//        /*
//        1.LoadType.REFRESH：首次访问 或者调用 PagingDataAdapter.refresh() 触发
//        2.LoadType.PREPEND：在当前列表头部添加数据的时候时触发，在项目中基本很少会用到直接返回 MediatorResult.Success(endOfPaginationReached = true) ，参数 endOfPaginationReached 表示没有数据了不在加载
//        3.LoadType.APPEND：加载更多时触发，这里获取下一页的 key, 如果 key 不存在，表示已经没有更多数据，直接返回 MediatorResult.Success(endOfPaginationReached = true) 不会在进行网络和数据库的访问
//         */
//        try {
//            val pageKey : Int? = when(loadType){
//                LoadType.REFRESH -> null
//                LoadType.PREPEND -> return MediatorResult.Success(true)
//                LoadType.APPEND -> {
//                    //使用remoteKey来获取下一个或上一个页面。
//                    val remoteKey =
//                        state.lastItemOrNull()?.id?.let {
//                            db.remoteKeyDao().remoteKeysId(it)
//                        }
//                    //remoteKey为 null ，意味着在初始刷新后没有加载任何项目，也没有更多的项目要加载。
//                    if (remoteKey?.nextKey == null) {
//                        return MediatorResult.Success(true)
//                    }
//                    remoteKey.nextKey
//                    }
//                }
//
//            val page = pageKey ?: 0
//
//            //从网络上请求数据
//            val result = api.getProjectList(page,cid).data
//
//            result.forEach {
//                it.id = path
//            }
//
//            val endOfPaginationReached = result.isEmpty()
//
//            db.withTransaction {
//                if (loadType == LoadType.REFRESH) {
//                    //清空数据
//                    db.remoteKeyDao().clearRemoteKeys(path)
//                    db.projectListDao().clearListById(path)
//                }
//                val prevKey = if (page == 0) null else page - 1
//                val nextKey = if (endOfPaginationReached) null else page + 1
//                val keys = result.map {
//                    RemoteKey(
//                        id = it.id,
//                        prevKey = prevKey,
//                        nextKey = nextKey,
//                    )
//                }
//                db.remoteKeyDao().insertAll(keys)
//                db.projectListDao().insertArticle(projectDataList = result)
//            }
//            return MediatorResult.Success(endOfPaginationReached)
//        }catch (e: IOException) {
//            return MediatorResult.Error(e)
//        } catch (e: HttpException) {
//            return MediatorResult.Error(e)
//        }
//
//    }
//
//
//
//
//}
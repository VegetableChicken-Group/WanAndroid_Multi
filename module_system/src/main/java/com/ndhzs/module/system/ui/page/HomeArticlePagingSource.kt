package com.ndhzs.module.system.ui.page

import android.util.Log
import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.ndhzs.module.system.logic.model.DataX
import com.ndhzs.module.system.logic.net.ApiService
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.HttpException
import java.io.IOException

/**
 *
 * @author :WhiteNight123(Guo Xiaoqiang)
 * @email 1448375249@qq.com
 * @date 2022/6/5
 */
class HomeArticlePagingSource : RxPagingSource<Int, DataX>() {


    override fun getRefreshKey(state: PagingState<Int, DataX>): Int? {
        return null
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, DataX>> {
        val nextPage = params.key ?: 1
        return ApiService.INSTANCE.getHomeArticle(nextPage)
            .subscribeOn(Schedulers.io())
            .map<LoadResult<Int, DataX>> { result ->
                Log.e("TAGsaadsac", "loadSingle: ${result.data.toString()}")
                LoadResult.Page(
                    data = result.data.datas,
                    prevKey = if (nextPage == 1) null else nextPage - 1,
                    nextKey = if (!result.data.over) nextPage + 1 else null,
                )
            }.onErrorReturn { e ->
                when (e) {
                    is IOException -> LoadResult.Error(e)
                    is HttpException -> LoadResult.Error(e)
                    else -> throw e
                }

            }
    }


//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HomeArticleViewModel> {
//        return try {
//            val nextPage = params.key ?:1
//            val response = HomeArticleRepository.getHomeArticle(nextPage)
//            LoadResult.Page(
//                data = response.data.datas,
//                prevKey = if(nextPage==1) null else nextPage-1,
//                nextKey = if(nextPage<response.data.pageCount) nextPage+1 else null
//            )
//        }catch (e:Exception){
//            LoadResult.Error(e)
//        }
//    }
}

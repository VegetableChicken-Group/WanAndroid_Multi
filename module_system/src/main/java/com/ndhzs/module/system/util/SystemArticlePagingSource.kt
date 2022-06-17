package com.ndhzs.module.system.util

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ndhzs.module.system.bean.SystemArticle
import com.ndhzs.module.system.network.SystemArticleService

/**
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/6/16 19:15
 */
class SystemArticlePagingSource(private val cid: String) : PagingSource<Int, SystemArticle.Data.Data>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SystemArticle.Data.Data> {
        return try {
            val page = params.key ?: 1
            val pageSize = params.loadSize
            val articles =
                SystemArticleService.INSTANCE.getSystemArticle(page,cid,pageSize)
            val data =articles.data.datas
            val prevKey = if (page > 1) page - 1 else null
            val nextKey = if (data.isNotEmpty()) page + 1 else null
            LoadResult.Page(articles.data.datas, prevKey, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, SystemArticle.Data.Data>): Int? {
        return null
    }
}
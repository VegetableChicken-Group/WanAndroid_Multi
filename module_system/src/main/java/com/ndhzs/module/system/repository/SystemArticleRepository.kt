package com.ndhzs.module.system.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ndhzs.module.system.bean.SystemArticle
import com.ndhzs.module.system.util.SystemArticlePagingSource
import kotlinx.coroutines.flow.Flow

/**
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/6/16 19:39
 */
object SystemArticleRepository {
    const val PAGE_SIZE = 10
    fun getPagingData(cid: String): Flow<PagingData<SystemArticle.Data.Data>> {
        return Pager(config = PagingConfig(pageSize = PAGE_SIZE), pagingSourceFactory = {
            SystemArticlePagingSource(cid)
        }).flow
    }
}
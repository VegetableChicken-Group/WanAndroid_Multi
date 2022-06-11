package com.ndhzs.module.home.repo.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ndhzs.module.home.repo.api.HomeWebService
import com.ndhzs.module.home.repo.bean.wrapper.HomeRvItemWrapper

/**
 * com.ndhzs.module.home.repo.source.PagingSource
 * WanAndroid_Multi
 *
 * @author 寒雨
 * @since 2022/6/1 21:25
 **/
class HomePagingSource : PagingSource<Int, HomeRvItemWrapper>() {
    override fun getRefreshKey(state: PagingState<Int, HomeRvItemWrapper>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HomeRvItemWrapper> {
        val pos = params.key ?: 0
        return try {
            val pageList = mutableListOf<HomeRvItemWrapper>()
            // 只有第一页显示banner
            if (pos == 0) {
                val bannerData = HomeWebService.INSTANCE.getBanner()
                bannerData.throwApiExceptionIfFail()
                pageList.add(
                    HomeRvItemWrapper(
                        HomeRvItemWrapper.Type.BANNER,
                        bannerData.data
                    )
                )
                // 置顶文章数据
                val topData = HomeWebService.INSTANCE.getTopArticles()
                topData.throwApiExceptionIfFail()
                pageList.addAll(topData.data.map { HomeRvItemWrapper(HomeRvItemWrapper.Type.ARTICLE, it.apply { top = true }) })
            }
            val listData = HomeWebService.INSTANCE.getArticles(page = pos, pageSize = 10)
            listData.throwApiExceptionIfFail()
            pageList.addAll(listData.data.datas.map { HomeRvItemWrapper(HomeRvItemWrapper.Type.ARTICLE, it) })
            LoadResult.Page(
                pageList,
                if (pos <= 0) null else pos - 1,
                if (pageList.isEmpty()) null else pos + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}
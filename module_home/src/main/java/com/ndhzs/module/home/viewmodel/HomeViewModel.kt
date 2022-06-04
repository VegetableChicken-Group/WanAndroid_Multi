package com.ndhzs.module.home.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.ndhzs.lib.common.extensions.toast
import com.ndhzs.lib.common.ui.mvvm.BaseViewModel
import com.ndhzs.module.home.repo.api.HomeWebService
import com.ndhzs.module.home.repo.source.HomePagingSource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * com.ndhzs.module.home.viewmodel.HomeViewModel
 * WanAndroid_Multi
 *
 * @author 寒雨
 * @since 2022/5/31 0:24
 **/
class HomeViewModel : BaseViewModel() {
    val pagingData = Pager(
        config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = false,
            initialLoadSize = 10
        ),
        pagingSourceFactory = {
            HomePagingSource()
        }
    ).flow.cachedIn(viewModelScope)

    fun setCollectState(id: Int, like: Boolean): Job {
        return viewModelScope.launch(
            CoroutineExceptionHandler { _, throwable -> "网络请求异常: ${throwable::class.java.name}: ${throwable.message}".toast() }
        ) {
            if (like) {
                HomeWebService.INSTANCE.like(id).throwApiExceptionIfFail()
            } else {
                HomeWebService.INSTANCE.dislike(id).throwApiExceptionIfFail()
            }
        }
    }
}
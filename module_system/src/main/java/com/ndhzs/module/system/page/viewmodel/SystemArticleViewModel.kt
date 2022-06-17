package com.ndhzs.module.system.page.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ndhzs.module.system.repository.SystemArticleRepository

/**
 * description ： TODO:类的作用
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/6/13 18:53
 */
class SystemArticleViewModel : ViewModel() {
    fun getSystemArticle(cid: String) = SystemArticleRepository.getPagingData(cid).cachedIn(viewModelScope)
}
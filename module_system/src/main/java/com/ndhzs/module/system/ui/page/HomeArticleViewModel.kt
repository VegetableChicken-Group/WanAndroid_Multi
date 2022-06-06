package com.ndhzs.module.system.ui.page

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.ndhzs.lib.common.ui.BaseViewModel

/**
 *
 * @author :WhiteNight123(Guo Xiaoqiang)
 * @email 1448375249@qq.com
 * @date 2022/6/5
 */
class HomeArticleViewModel:BaseViewModel() {
    val homeArticle = Pager(PagingConfig(pageSize = 20)){
        HomeArticlePagingSource()
    }.flow.cachedIn(viewModelScope)
}
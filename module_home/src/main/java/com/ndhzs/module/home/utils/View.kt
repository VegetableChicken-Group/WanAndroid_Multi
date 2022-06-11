package com.ndhzs.module.home.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * com.ndhzs.module.home.utils.View
 * WanAndroid_Multi
 *
 * @author 寒雨
 * @since 2022/6/2 14:43
 **/
fun RecyclerView.scrollToTop() {
    if ((layoutManager as LinearLayoutManager).findFirstVisibleItemPosition() > 20) {
        scrollToPosition(0)
    } else {
        smoothScrollToPosition(0)
    }
}
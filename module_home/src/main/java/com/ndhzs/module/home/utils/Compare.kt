package com.ndhzs.module.home.utils

/**
 * com.ndhzs.module.home.utils.Compare
 * WanAndroid_Multi
 *
 * @author 寒雨
 * @since 2022/6/2 0:35
 **/
fun <T> List<T>.allTheSame(otherList: List<T>, compare: (item1: T, item2: T) -> Boolean): Boolean {
    if (size != otherList.size) return false
    forEachIndexed { index, t ->
        if (compare(t, otherList[index]))
            return false
    }
    return true
}
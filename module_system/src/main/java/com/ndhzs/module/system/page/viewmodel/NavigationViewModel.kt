package com.ndhzs.module.system.page.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.ndhzs.lib.common.extensions.safeSubscribeBy
import com.ndhzs.module.system.bean.Navigation
import com.ndhzs.module.system.network.NavigationService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlin.collections.ArrayList

/**==
author : Watermelon02
email : 1446157077@qq.com
date : 2022/5/31 15:48
=*/
class NavigationViewModel : ViewModel() {
    @Volatile
    var navigation: Navigation? = null

    fun getNavigation(successAction: (navigation: Navigation) -> Unit) {
        if (navigation == null) {
            synchronized(this) {
                if (navigation == null) {
                    NavigationService.INSTANCE.getNavigation().subscribeOn(Schedulers.io())  // 线程切换
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnError {
                            Log.d("testTag", "NavigationVM:${it.message}")
                        }
                        .safeSubscribeBy {
                            // 如果是网络连接错误，则这里会默认处理
                            // 成功的时候
                            this.navigation = it
                            successAction(it)
                        }
                }
            }
        }else return successAction(navigation!!)
    }

    fun getArticlesSortedByChapter(articles: List<Navigation.Data.Article>): HashMap<String, ArrayList<Navigation.Data.Article>> {
        val chapters = HashMap<String, ArrayList<Navigation.Data.Article>>()
        for (article in articles) {
            if (!chapters.containsKey(article.chapterName)) {
                chapters[article.chapterName] = ArrayList()
            }
            chapters[article.chapterName]?.add(article)
        }
        return chapters
    }
}
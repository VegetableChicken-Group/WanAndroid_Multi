package com.ndhzs.module.system.page.viewmodel

import android.os.Binder
import android.util.Log
import androidx.lifecycle.ViewModel
import com.ndhzs.lib.common.network.ApiWrapper
import com.ndhzs.module.system.bean.Navigation
import com.ndhzs.module.system.network.NavigationService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlin.collections.ArrayList

/**==
author : Watermelon02
email : 1446157077@qq.com
date : 2022/5/31 15:48
=*/
class NavigationViewModel : ViewModel() {
    @Volatile
    private var navigation: Navigation? = null
    fun getNavigation(successAction: (navigation: Navigation) -> Unit) {
        if (navigation == null) {
            synchronized(this) {
                if (navigation == null) {
                    NavigationService.INSTANCE.getNavigation().subscribeOn(Schedulers.io())  // 线程切换
                        .observeOn(AndroidSchedulers.mainThread())
                        .safeSubscribe(object : SingleObserver<Navigation> {
                            override fun onSubscribe(d: Disposable) {
                            }

                            override fun onError(e: Throwable) {
                                Log.d("testTag", "NavigationViewModel:onError: ${e.message}")
                            }

                            override fun onSuccess(t: Navigation) {
                                successAction(t)
                                navigation = t
                            }

                        })
                } else successAction(navigation!!)
            }
        } else return successAction(navigation!!)
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
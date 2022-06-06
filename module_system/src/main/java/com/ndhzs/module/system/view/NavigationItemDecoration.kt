package com.ndhzs.module.system.view

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexboxLayoutManager
import com.ndhzs.module.system.bean.Navigation

/**
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/6/1 16:54
 */
class NavigationItemDecoration(val articles:List<Navigation.Article>) : RecyclerView.ItemDecoration() {
    companion object{
        const val TEXT_HEIGHT = 100f
    }
    val paint = Paint()
    var lastChapterName = ""
    var totalY = 0f
    init {
        paint.textSize = TEXT_HEIGHT
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        val layoutManager = parent.layoutManager as FlexboxLayoutManager
        for (i in layoutManager.findFirstVisibleItemPosition() .. layoutManager.findLastVisibleItemPosition()) {
            val article: Navigation.Article = articles[i]
            if (article.chapterName!= lastChapterName){
                val child = parent.getChildAt(i)
                c.drawText(article.chapterName,child.x,child.y+ TEXT_HEIGHT,paint)
                lastChapterName = article.chapterName
            }
        }
        totalY = 0f
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        for (i in articles.indices) {
            val article: Navigation.Article = articles[i]
            if (article.chapterName!= lastChapterName){

                lastChapterName = article.chapterName
            }
        }
    }
}
package com.ndhzs.module.square.bean

import java.util.*
import kotlin.collections.ArrayList

/**
 * @ClassName ItemBean
 * @author Silence~
 * @date 2022/6/1 16:42
 * @Description 储存广场界面中每一个item的数据
 */
class ItemListBean {
    var data = Data()
    var errorCode = 0
    var errorMsg = ""

    class Data {
        var datas = ArrayList<ArrayList<Item>>()
        var curPage = 0
        var offset = 0
        var over = false
        var pageCount = 202
        var size = 20
        var total = 4027
    }

    class Item{
        var apkLink = ""
        var audit = 1
        var author = ""
        var canEdit = false
        var chapterId = 494
        var chapterName = "广场" //use
        var collect = false //use
        var courseId = 13
        var desc = ""
        var descMd = ""
        var envelopePic = ""
        var fresh = true //use
        var host = ""
        var id = 22878
        var link = "https://juejin.cn/post/7103706223555379237" //use
        var niceDate = "8小时前" //use
        var niceShareDate = "8小时前"
        var origin = ""
        var prefix = ""
        var projectLink = ""
        var publishTime = 1654043816000
        var realSuperChapterId = 493
        var selfVisible = 0
        var shareDate = 1654043816000
        var shareUser = "goweii" //use
        var superChapterId = 494
        var superChapterName = "广场Tab" //use
        var tags = ArrayList<Objects>()
        var title = "并发编程-阻塞队列和线程池原理" //use
        var type = 0
        var userId = 20382
        var visible = 0
        var zan = 0
    }
}
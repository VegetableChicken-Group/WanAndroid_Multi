package com.ndhzs.module.square.bean

/**
 * @ClassName ItemBean
 * @author Silence~
 * @date 2022/6/1 16:42
 * @Description 储存广场界面中每一个item的数据
 */
class ItemListBean {
    private lateinit var itemList: List<ArrayList<ItemBean>>
    private var curPage = 0

    fun getTranslateResult() : List<ArrayList<ItemBean>>{
        return itemList
    }

    class ItemBean{
        /*"apkLink": "",
        "audit": 1,
        "author": "",
        "canEdit": false,
        "chapterId": 494,
        "chapterName": "广场",
        "collect": false,
        "courseId": 13,
        "desc": "",
        "descMd": "",
        "envelopePic": "",
        "fresh": true,
        "host": "",
        "id": 22878,
        "link": "https://juejin.cn/post/7103706223555379237",
        "niceDate": "8小时前",
        "niceShareDate": "8小时前",
        "origin": "",
        "prefix": "",
        "projectLink": "",
        "publishTime": 1654043816000,
        "realSuperChapterId": 493,
        "selfVisible": 0,
        "shareDate": 1654043816000,
        "shareUser": "goweii",
        "superChapterId": 494,
        "superChapterName": "广场Tab",
        "tags": [],
        "title": "并发编程-阻塞队列和线程池原理",
        "type": 0,
        "userId": 20382,
        "visible": 0,
        "zan": 0
         */
    }
}
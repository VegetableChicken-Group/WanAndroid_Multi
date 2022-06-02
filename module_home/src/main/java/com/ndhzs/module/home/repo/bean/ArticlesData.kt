package com.ndhzs.module.home.repo.bean

/**
 * com.ndhzs.module.home.repo.bean.ArticlesData
 * WanAndroid_Multi
 *
 * @author 寒雨
 * @since 2022/6/1 21:17
 **/
data class ArticlesData(
    val curPage: Int,
    val datas: List<ArticleData>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)

data class ArticleData(
    val apkLink: String,
    val audit: Int,
    var author: String,
    val canEdit: Boolean,
    val chapterId: Int,
    val chapterName: String,
    var collect: Boolean,
    val courseId: Int,
    val desc: String,
    val descMd: String,
    val envelopePic: String,
    val fresh: Boolean,
    val host: String,
    val id: Int,
    val link: String,
    val niceDate: String,
    val niceShareDate: String,
    val origin: String,
    val prefix: String,
    val projectLink: String,
    val publishTime: Long,
    val realSuperChapterId: Int,
    val selfVisible: Int,
    val shareDate: Long,
    var shareUser: String,
    val superChapterId: Int,
    val superChapterName: String,
    val tags: List<Tag>,
    var title: String,
    val type: Int,
    val userId: Int,
    val visible: Int,
    val zan: Int,
) {
    data class Tag(
        val name: String,
        val url: String,
    )
}
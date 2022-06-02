package com.ndhzs.module.project.bean

import androidx.room.Entity
import androidx.room.TypeConverters
import com.ndhzs.module.project.db.TagTypeConverter

/**
 *
 * @ProjectName:    WanAndroid_Multi
 * @Package:        com.ndhzs.module.project.bean
 * @ClassName:      ProjectList
 * @Author:         Yan
 * @CreateDate:     2022年06月01日 22:30:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    项目列表数据
 */

@Entity(tableName = "tab_project")
@TypeConverters(TagTypeConverter::class)
data class ProjectList(
    val apkLink: String,
    val audit: Int,
    val author: String,
    val canEdit: Boolean,
    val chapterId: Int,
    val chapterName: String,
    val collect: Boolean,
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
    val shareUser: String,
    val superChapterId: Int,
    val superChapterName: String,
    val tags: List<Tag>,
    val title: String,
    val type: Int,
    val userId: Int,
    val visible: Int,
    val zan: Int
)

data class Tag(
    val name: String,
    val url: String
)
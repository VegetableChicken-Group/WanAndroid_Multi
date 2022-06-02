package com.ndhzs.module.project.bean

/**
 *
 * @ProjectName:    WanAndroid_Multi
 * @Package:        com.ndhzs.module.project.bean
 * @ClassName:      ProjectTree
 * @Author:         Yan
 * @CreateDate:     2022年06月01日 22:25:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    项目的分类
 */

data class ProjectTree(
    val author: String,
    val children: List<Any>,
    val courseId: Int,
    val cover: String,
    val desc: String,
    val id: Int,
    val lisense: String,
    val lisenseLink: String,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)
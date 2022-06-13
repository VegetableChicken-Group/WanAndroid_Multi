package com.ndhzs.module.system.bean

data class SystemData(
    val data: List<Data>,
    val errorCode: Int,
    val errorMsg: String
) {
    data class Data(
        val author: String,
        val children: List<Children>,
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
    ) {
        data class Children(
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
    }
}
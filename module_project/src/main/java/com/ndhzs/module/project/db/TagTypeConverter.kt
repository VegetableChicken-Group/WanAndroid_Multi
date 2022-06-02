package com.ndhzs.module.project.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ndhzs.module.project.bean.Tag

/**
 *
 * @ProjectName:    WanAndroid_Multi
 * @Package:        com.ndhzs.module.project.db
 * @ClassName:      TagTypeConverter
 * @Author:         Yan
 * @CreateDate:     2022年06月02日 16:48:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    List<Tag>的类型转换
 */

class TagTypeConverter {
    @TypeConverter
    fun stringToObject(value: String): List<Tag> {
        val listType = object : TypeToken<List<Tag>>() {
        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun objectToString(list: List<Tag>): String {
        return Gson().toJson(list)
    }
}
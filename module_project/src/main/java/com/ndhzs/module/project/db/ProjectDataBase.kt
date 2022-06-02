package com.ndhzs.module.project.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ndhzs.lib.common.extensions.appContext
import com.ndhzs.module.project.bean.ProjectList

/**
 *
 * @ProjectName:    WanAndroid_Multi
 * @Package:        com.ndhzs.module.project.db
 * @ClassName:      ProjectDataBase
 * @Author:         Yan
 * @CreateDate:     2022年06月02日 15:34:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    项目板块的数据库
 */


@Database(
    entities = [ProjectList::class],
    version = 1,
    exportSchema = false
)

abstract class ProjectDataBase : RoomDatabase() {

    abstract fun projectListDao() : ProjectListDao

    companion object{
        private const val DB_NAME = "project_database"

        @Volatile
        private var instance : ProjectDataBase? = null

        fun get(context : Context) : ProjectDataBase{
            return instance ?: Room.databaseBuilder(context,ProjectDataBase::class.java, DB_NAME)
                .build()
                .also {
                    instance = it
                }

        }

    }

}
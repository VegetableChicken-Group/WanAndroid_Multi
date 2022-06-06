package com.ndhzs.module.project.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ndhzs.lib.common.extensions.appContext
import com.ndhzs.lib.common.extensions.toast
import com.ndhzs.module.project.bean.ProjectList
import com.ndhzs.module.project.bean.RemoteKey
import com.ndhzs.module.project.db.dao.ProjectListDao
import com.ndhzs.module.project.db.dao.RemoteKeyDao

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
    entities = [ProjectList::class,RemoteKey::class],
    version = 1,
    exportSchema = false
)

abstract class ProjectDataBase : RoomDatabase() {

    abstract fun projectListDao() : ProjectListDao
    abstract fun remoteKeyDao() : RemoteKeyDao

    companion object{
        private const val DB_NAME = "project_database.db"

        @Volatile
        private var instance : ProjectDataBase? = null

        fun get(context: Context) : ProjectDataBase{
            return instance ?: Room.databaseBuilder(context.applicationContext  ,ProjectDataBase::class.java, DB_NAME)
                .build()
                .also {
                    instance = it
                }
        }
    }
}
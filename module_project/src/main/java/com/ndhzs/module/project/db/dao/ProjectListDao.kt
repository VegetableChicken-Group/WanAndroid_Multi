package com.ndhzs.module.project.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ndhzs.module.project.bean.ProjectList

/**
 *
 * @ProjectName:    WanAndroid_Multi
 * @Package:        com.ndhzs.module.project.db
 * @ClassName:      ProjectListDao
 * @Author:         Yan
 * @CreateDate:     2022年06月02日 15:37:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    项目列表 DAO
 */

@Dao
interface ProjectListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProjects(projectDataList : List<ProjectList>)

    @Query("SELECT * FROM tab_project WHERE id =:id")
    fun queryLocalList(id : Int): PagingSource<Int, ProjectList>

    @Query("SELECT * FROM tab_project ")
    fun queryLocalList(): PagingSource<Int, ProjectList>

    @Query("DELETE FROM tab_project WHERE id=:id")
    suspend fun clearListById(id: Int)

    @Query("DELETE FROM tab_project")
    suspend fun clearList()


}
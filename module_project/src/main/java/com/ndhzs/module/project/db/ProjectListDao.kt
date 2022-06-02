package com.ndhzs.module.project.db

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
    suspend fun insertArticle(projectDataList : List<ProjectList>)

    @Query("SELECT * FROM tab_project WHERE id =:id")
    fun queryLocalArticle(id: Int): PagingSource<Int, List<ProjectList>>

    @Query("DELETE FROM tab_project WHERE id=:id")
    suspend fun clearArticleByType(id: Int)


}
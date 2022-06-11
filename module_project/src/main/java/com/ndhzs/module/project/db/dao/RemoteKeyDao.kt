package com.ndhzs.module.project.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ndhzs.module.project.bean.RemoteKey

@Dao
interface RemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RemoteKey>)

    @Query("SELECT * FROM tab_remote_key WHERE projectId = :projectId ")
    suspend fun remoteKeysId(projectId: Int): RemoteKey?

    @Query("DELETE FROM tab_remote_key WHERE projectId =:projectId")
    suspend fun clearRemoteKeysId(projectId: Int)

    @Query("DELETE FROM tab_remote_key")
    suspend fun clearRemoteKeys()
}
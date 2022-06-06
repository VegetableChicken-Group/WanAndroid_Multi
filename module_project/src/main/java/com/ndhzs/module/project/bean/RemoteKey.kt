package com.ndhzs.module.project.bean

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tab_remote_key")
data class RemoteKey(
    @PrimaryKey
    val id: Int,

    val prevKey: Int?,
    val nextKey: Int?
)
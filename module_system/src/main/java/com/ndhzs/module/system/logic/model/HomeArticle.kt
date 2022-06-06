package com.ndhzs.module.system.logic.model

import com.ndhzs.module.system.logic.model.Data

data class HomeArticle(
    val `data`: Data,
    val errorCode: Int,
    val errorMsg: String
)
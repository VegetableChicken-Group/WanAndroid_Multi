package com.ndhzs.module.main

import com.alibaba.android.arouter.facade.template.IProvider
import kotlinx.coroutines.flow.MutableSharedFlow
import java.io.*



interface IMainService: IProvider {

    val dataFlow: MutableSharedFlow<Data>

    data class Data(
        val name: String,
        val stuNum: String
    ) : Serializable
}
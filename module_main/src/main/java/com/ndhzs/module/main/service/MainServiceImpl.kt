package com.ndhzs.module.main.service

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.ndhzs.lib.common.config.MAIN_SERVICE
import com.ndhzs.module.main.IMainService
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow

@Route(path = MAIN_SERVICE)
class MainServiceImpl:IMainService {

    private val _dataFlow = MutableSharedFlow<IMainService.Data>()

    override val dataFlow: MutableSharedFlow<IMainService.Data>
        get() = _dataFlow


    override fun init(context: Context?) {

    }


}
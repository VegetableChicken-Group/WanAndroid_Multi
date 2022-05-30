package com.ndhzs.module.main.service

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.ndhzs.lib.common.config.MAIN_SERVICE
import com.ndhzs.module.main.IMainService
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

/**
 * com.ndhzs.module.main.service.MainService
 * WanAndroid_Multi
 *
 * @author 寒雨
 * @since 2022/5/30 23:46
 **/
@Route(path = MAIN_SERVICE)
class MainService : IMainService {

    private lateinit var mContext: Context

    private val _fabClickState = MutableSharedFlow<Unit>()

    override val fabClickState: MutableSharedFlow<Unit>
        get() = _fabClickState

    override fun init(context: Context) {
        mContext = context
    }
}
package com.ndhzs.module.main.service

import android.content.Context
import androidx.appcompat.app.ActionBar
import com.alibaba.android.arouter.facade.annotation.Route
import com.ndhzs.lib.common.config.MAIN_SERVICE
import com.ndhzs.module.main.IMainService
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
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

    override val fabClickState: MutableSharedFlow<Unit> = MutableSharedFlow()

    override fun registerActionBarAction(route: String, action: ActionBar.() -> Unit) {
        actionMap[route] = action
        actionBarActionFlow.tryEmit(action)
    }

    override fun init(context: Context) {
        mContext = context
    }

    companion object {
        val actionMap = HashMap<String, ActionBar.() -> Unit>()

        val actionBarActionFlow = MutableSharedFlow<ActionBar.() -> Unit>()
    }
}
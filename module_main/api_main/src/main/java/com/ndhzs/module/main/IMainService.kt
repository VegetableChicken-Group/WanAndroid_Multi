package com.ndhzs.module.main

import androidx.appcompat.app.ActionBar
import com.alibaba.android.arouter.facade.template.IProvider
import kotlinx.coroutines.flow.MutableSharedFlow

/**
 * com.ndhzs.module.main.MainService
 * WanAndroid_Multi
 *
 * @author 寒雨
 * @since 2022/5/30 23:02
 **/
interface IMainService : IProvider {

    /**
     * collect它可以订阅fab的点击事件
     */
    val fabClickState: MutableSharedFlow<Unit>

    /**
     * 为对应fragment注册翻页时actionbar动作
     *
     * @param route
     * @param action
     */
    fun registerActionBarAction(route: String, action: ActionBar.() -> Unit)
}
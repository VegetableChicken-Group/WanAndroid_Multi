package com.ndhzs.module.main

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

    val fabClickState: MutableSharedFlow<Unit>

}
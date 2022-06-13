package com.ndhzs.module.main.viewmodel

import androidx.lifecycle.ViewModel
import com.ndhzs.lib.common.ui.mvvm.BaseViewModel
import com.ndhzs.module.main.repo.web.MainWebService

/**
 * com.ndhzs.module.main.viewmodel.MainViewModel
 * WanAndroid_Multi
 *
 * @author 寒雨
 * @since 2022/5/29 13:43
 **/
class MainViewModel : BaseViewModel() {
    fun getUserInfo() = MainWebService.INSTANCE.getInfo()
}
package com.ndhzs.module.project.ui.viewmodel

import com.ndhzs.lib.common.ui.mvvm.BaseViewModel
import com.ndhzs.module.project.bean.ProjectList

/**
 *
 * @ProjectName:    WanAndroid_Multi
 * @Package:        com.ndhzs.module.project.ui.viewmodel
 * @ClassName:      ProjectViewModel
 * @Author:         Yan
 * @CreateDate:     2022年06月01日 23:10:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    项目模块的ViewModel
 */
class ProjectViewModel : BaseViewModel(){

    val projectTree = mutableListOf<List<ProjectList>>()
    val projectList = mutableListOf<List<ProjectList>>()




}
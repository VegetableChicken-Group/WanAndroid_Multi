package com.ndhzs.module.project.ui.fragment

import android.os.Bundle
import android.view.View
import com.ndhzs.lib.common.ui.mvvm.BaseVmBindFragment
import com.ndhzs.module.project.ui.viewmodel.ProjectViewModel
import com.ndhzs.module.test.databinding.ProjectFragmentMainBinding

/**
 *
 * @ProjectName:    WanAndroid_Multi
 * @Package:        com.ndhzs.module.project.ui.fragment
 * @ClassName:      ProjectFragment
 * @Author:         Yan
 * @CreateDate:     2022年06月01日 22:57:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    项目的主界面 用Fragment实现UI
 */
class ProjectFragment : BaseVmBindFragment<ProjectViewModel,ProjectFragmentMainBinding>(){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

}
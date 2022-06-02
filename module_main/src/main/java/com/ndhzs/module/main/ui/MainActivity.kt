package com.ndhzs.module.main.ui

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.launcher.ARouter
import com.ndhzs.api.test.ITestService
import com.ndhzs.lib.common.extensions.toast
import com.ndhzs.lib.common.service.ServiceManager
import com.ndhzs.lib.common.ui.BaseActivity
import com.ndhzs.lib.common.ui.mvvm.BaseVmBindActivity
import com.ndhzs.module.main.IMainService
import com.ndhzs.module.main.R
import com.ndhzs.module.main.databinding.MainActivityMainBinding
import com.ndhzs.module.main.viewmodel.MainActivityViewModel
import kotlinx.coroutines.launch

class MainActivity : BaseVmBindActivity<MainActivityViewModel,MainActivityMainBinding>(){


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)


    lifecycleScope.launch{
      ARouter.getInstance()
        .navigation(IMainService::class.java)
        .dataFlow
        .emit(IMainService.Data("",""))
    }


  }
}
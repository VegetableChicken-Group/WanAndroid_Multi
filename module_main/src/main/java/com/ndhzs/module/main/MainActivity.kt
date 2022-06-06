package com.ndhzs.module.main

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter
import com.ndhzs.api.test.ITestService
import com.ndhzs.lib.common.config.SYSTEM_ENTRY
import com.ndhzs.lib.common.config.TEST_ENTRY
import com.ndhzs.lib.common.config.TEST_SHOW
import com.ndhzs.lib.common.extensions.toast
import com.ndhzs.lib.common.service.ServiceManager
import com.ndhzs.lib.common.ui.BaseActivity

class MainActivity : BaseActivity() {
  
  private val mBtnOpenTestActivity: Button by R.id.main_btn_open_test_activity.view()
  private val mBtnShowFragment: Button by R.id.main_btn_show_test_show_fragment.view()
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_activity_main)
    
//    mBtnOpenTestActivity.setOnClickListener {
//      toast("启动 TestActivity")
//      ServiceManager(ITestService::class)
//        .startTestActivity(
//          this,
//          ITestService.Data(
//            "123", "12345"
//          )
//        )
//    }
    mBtnOpenTestActivity.setOnClickListener {
      ARouter.getInstance().build(SYSTEM_ENTRY).navigation()
    }
    
    // 观察 liveData
    ServiceManager(ITestService::class).liveData.observe {
      // ......
    }
    
    mBtnShowFragment.setOnClickListener {
      replaceFragment(R.id.main_fcv_show) {
        toast("启动 TestShowFragment")
        ServiceManager.fragment(TEST_SHOW)
      }
    }
  }
}
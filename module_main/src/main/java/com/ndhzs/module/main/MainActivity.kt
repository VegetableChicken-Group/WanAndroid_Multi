package com.ndhzs.module.main

import android.os.Bundle
import android.widget.Button
import com.ndhzs.api.test.ITestService
import com.ndhzs.api.test.TEST_SHOW
import com.ndhzs.lib.base.ui.BaseActivity
import com.ndhzs.lib.utils.service.ServiceManager
import com.ndhzs.lib.utils.service.impl

class MainActivity : BaseActivity() {
  
  private val mBtnOpenTestActivity: Button by R.id.main_btn_open_test_activity.view()
  private val mBtnShowFragment: Button by R.id.main_btn_show_test_show_fragment.view()
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_activity_main)
    
    mBtnOpenTestActivity.setOnClickListener {
      toast("启动 TestActivity")
      ITestService::class.impl
        .startTestActivity(
          this,
          ITestService.Data(
            "123", "12345"
          )
        )
    }

    // 观察 liveData
    ITestService::class.impl.liveData.observe {
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
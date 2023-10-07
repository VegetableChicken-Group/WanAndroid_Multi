package com.ndhzs.module.main

import android.os.Bundle
import android.widget.Button
import com.g985892345.provider.init.KtProviderInitializer
import com.ndhzs.api.test.ITestService
import com.ndhzs.api.test.TEST_SHOW
import com.ndhzs.lib.base.ui.BaseActivity
import com.ndhzs.lib.config.route.LOGIN_ENTRY
import com.ndhzs.lib.utils.extensions.setOnSingleClickListener
import com.ndhzs.lib.utils.service.ServiceManager
import com.ndhzs.lib.utils.service.impl

class MainActivity : BaseActivity() {
  
  // 封装的一种代替 findViewById 的方法
  private val mBtnOpenTestActivity: Button by R.id.main_btn_open_test_activity.view()
  private val mBtnShowFragment: Button by R.id.main_btn_show_test_show_fragment.view()
  private val mBtnLogin by R.id.main_btn_login.view<Button>()
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_activity_main)
    initClick()
    initObserve()
  }
  
  private fun initClick() {
    // 使用 setOnSingleClickListener 防抖 (即 500 毫秒内的多次点击无效)
    mBtnOpenTestActivity.setOnSingleClickListener {
      toast("启动 TestActivity")
      ITestService::class.impl
        .startTestActivity(
          this,
          ITestService.Data(
            "123", "12345"
          )
        )
    }
  
    mBtnShowFragment.setOnSingleClickListener {
      replaceFragment(R.id.main_fcv_show) {
        toast("启动 TestShowFragment")
        ServiceManager.fragment(TEST_SHOW)
      }
    }
  
    mBtnLogin.setOnSingleClickListener {
      // 进入登录界面
      ServiceManager.activity(LOGIN_ENTRY)
    }
  }
  
  private fun initObserve() {
    android.util.Log.d("ggg", "(${Exception().stackTrace[0].run { "$fileName:$lineNumber" }}) -> " +
      "Single: ITestService = ${KtProviderInitializer.SingleImplProviderMap[ITestService::class]}")
    android.util.Log.d("ggg", "(${Exception().stackTrace[0].run { "$fileName:$lineNumber" }}) -> " +
        "New: ITestService = ${KtProviderInitializer.NewImplProviderMap[ITestService::class]}")
    // 观察 liveData
    ITestService::class.impl.liveData.observe {
      // ......
    }
  }
}
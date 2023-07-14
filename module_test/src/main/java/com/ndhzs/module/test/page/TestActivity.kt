package com.ndhzs.module.test.page

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.content.edit
import com.g985892345.provider.annotation.KClassProvider
import com.ndhzs.api.test.ITestService
import com.ndhzs.lib.base.ui.BaseActivity
import com.ndhzs.lib.config.route.TEST_ENTRY
import com.ndhzs.lib.config.sp.SP_TEST_DEMO
import com.ndhzs.lib.config.sp.defaultSp
import com.ndhzs.lib.utils.extensions.*
import com.ndhzs.module.test.R

@KClassProvider(name = TEST_ENTRY)
class TestActivity : BaseActivity() {
  
  // 官方写的获取 ViewModel 的扩展函数
  // 如果需要带参数，你可以看看 BaseActivity 上的头注释
  private val mViewModel by viewModels<TestViewModel>()
  
  companion object {
    fun start(context: Context, data: ITestService.Data) {
      context.startActivity(
        Intent(context, TestActivity::class.java).apply {
          putExtra(TestActivity::mData.name, data)
        }
      )
    }
  }
  
  // 封装的一种代替 findViewById 的方法
  private val mTvShow by R.id.test_tv_show.view<TextView>()
  
  // 封装的一种快捷获取 intent 中的方法，具体用法可点进去
  private val mData by intent<ITestService.Data>()
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.test_activity_test)
    initData()
    initObserve()
    initClick()
  }
  
  private fun initData() {
    val oldStuNum = defaultSp.getString(SP_TEST_DEMO, null)
    if (oldStuNum != mData.stuNum) {
      defaultSp.edit {
        putString(SP_TEST_DEMO, mData.stuNum)
      }
    }
  }
  
  // 初始化观察流，这里写对 mViewModel 的观察
  private fun initObserve() {
    mViewModel.data.observe {
      toast("刷新成功")
      mTvShow.text = it.toString()
    }
  }
  
  private fun initClick() {
    // 使用 setOnSingleClickListener 防抖 (即 500 毫秒内的多次点击无效)
    mTvShow.setOnSingleClickListener {
      // 模拟点击后刷新数据
      mViewModel.refresh()
    }
  }
}
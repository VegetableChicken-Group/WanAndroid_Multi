package com.ndhzs.module.test.page

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.core.content.edit
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ndhzs.api.test.ITestService
import com.ndhzs.lib.base.ui.BaseActivity
import com.ndhzs.lib.config.route.TEST_ENTRY
import com.ndhzs.lib.config.sp.SP_TEST_DEMO
import com.ndhzs.lib.config.sp.defaultSp
import com.ndhzs.lib.utils.extensions.*
import com.ndhzs.module.test.R
import kotlinx.coroutines.launch

@Route(path = TEST_ENTRY)
class TestActivity : BaseActivity() {
  
  companion object {
    fun start(context: Context, data: ITestService.Data) {
      context.startActivity(
        Intent(context, TestActivity::class.java).apply {
          putExtra(ITestService.Data::class.java.name, data)
        }
      )
    }
  }
  
  private val mData by lazyUnlock {
    intent.getSerializableExtra(ITestService.Data::class.java.name) as ITestService.Data
  }
  
  private val mOldStuNum = defaultSp.getString(SP_TEST_DEMO, null)
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.test_activity_test)
    defaultSp.edit {
      putString(SP_TEST_DEMO, mData.stuNum)
    }
    
    launchCatch {
      throw RuntimeException()
    }.catch {
//      Log.d("ggg", "(TestActivity.kt:46) -> 抓取成功")
    }
    
    lifecycleScope.launch {
      repeatOnLifecycle(Lifecycle.State.STARTED) {
      
      }
      Log.d("ggg", "(TestActivity.kt:48) -> launch 中")
    }
    Log.d("ggg", "(TestActivity.kt:50) -> launch 后")
  }
}
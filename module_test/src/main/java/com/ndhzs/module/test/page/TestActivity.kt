package com.ndhzs.module.test.page

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.content.edit
import com.alibaba.android.arouter.facade.annotation.Route
import com.ndhzs.api.test.ITestService
import com.ndhzs.lib.common.config.TEST_ENTRY
import com.ndhzs.lib.common.extensions.SP_TEST_DEMO
import com.ndhzs.lib.common.extensions.defaultSp
import com.ndhzs.lib.common.extensions.lazyUnlock
import com.ndhzs.lib.common.ui.BaseActivity
import com.ndhzs.module.test.R

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
  }
}
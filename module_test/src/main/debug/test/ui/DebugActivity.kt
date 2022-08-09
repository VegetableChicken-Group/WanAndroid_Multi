package test.ui

import android.os.Bundle
import com.ndhzs.api.test.ITestService
import com.ndhzs.lib.base.BaseDebugActivity
import com.ndhzs.module.test.page.TestActivity

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2022/5/28 0:23
 */
class DebugActivity : BaseDebugActivity() {

  override fun onDebugCreate(savedInstanceState: Bundle?) {
    TestActivity.start(
      this,
      ITestService.Data(
        "123", "12345"
      )
    )
  }
}
package ui

import android.os.Bundle
import com.ndhzs.api.test.ITestService
import com.ndhzs.lib.common.ui.BaseActivity

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2022/5/28 0:23
 */
class DebugActivity : BaseActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    TestActivity.start(
      this,
      ITestService.Data(
        "123", "12345"
      )
    )
    finish()
  }
}
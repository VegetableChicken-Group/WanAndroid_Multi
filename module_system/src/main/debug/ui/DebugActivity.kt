package ui

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentManager
import com.alibaba.android.arouter.launcher.ARouter
//import com.ndhzs.api.test.ITestService
import com.ndhzs.lib.common.network.ApiGenerator
import com.ndhzs.lib.common.service.ServiceManager
import com.ndhzs.lib.common.ui.BaseActivity
import com.ndhzs.module.system.R
import com.ndhzs.module.system.page.ui.ContainerFragment

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2022/5/28 0:23
 */
class DebugActivity : BaseActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_debug)
    val fragment = ServiceManager.fragment("/system/container")
    supportFragmentManager.beginTransaction().replace(R.id.debug_fragment,fragment).commit()
  }
}
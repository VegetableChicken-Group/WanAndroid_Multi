package test.ui

import com.alibaba.android.arouter.facade.annotation.Route
import com.ndhzs.lib.single.ISingleModuleEntry
import com.ndhzs.lib.single.ui.SingleModuleActivity
import com.ndhzs.module.test.page.TestFragment

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2022/5/28 0:23
 */
@Route(path = "/single/test")
class TestSingleModuleEntry : ISingleModuleEntry {

  override fun getPage(activity: SingleModuleActivity): ISingleModuleEntry.Page {
    return ISingleModuleEntry.FragmentPage {
      TestFragment()
    }
//    如果要启动一个 activity
//    return ISingleModuleEntry.ActionPage {
//      startActivity(Intent(this, TestActivity::class.java))
//    }
  }
}
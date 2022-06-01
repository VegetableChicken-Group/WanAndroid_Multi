package ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.alibaba.android.arouter.launcher.ARouter
import com.ndhzs.lib.common.config.SQUARE_SHOW
//import com.ndhzs.api.test.ITestService
import com.ndhzs.lib.common.ui.BaseActivity
import com.ndhzs.module.square.R

/**
 * @ClassName DebugActivity
 * @author Silence~ (Zhu Zhaoting)
 * @date 2022/5/30 23:01
 */
class DebugActivity : BaseActivity() {
  private var mFragmentManager: FragmentManager? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.square_activity_debug)

    //获取fragment
    val squareFragment = ARouter.getInstance()
      .build(SQUARE_SHOW)
      .navigation() as Fragment

    //将fragment显示在FrameLayout上
    if (mFragmentManager == null) {
      mFragmentManager = this.supportFragmentManager
    }
    val transaction = mFragmentManager!!.beginTransaction()

    transaction.add(R.id.square_framelayout_fragment, squareFragment)
    transaction.commit()

  }

}
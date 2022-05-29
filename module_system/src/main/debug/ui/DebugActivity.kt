package ui

import android.os.Bundle
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.commit
import com.ndhzs.lib.common.ui.BaseActivity
import com.ndhzs.module.system.page.ui.SystemFragment
import kotlin.random.Random

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2022/5/28 0:23
 */
class DebugActivity : BaseActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    
    val fragmentContainerView = FragmentContainerView(this)
    fragmentContainerView.id = Random.nextInt()
    setContentView(fragmentContainerView)
    
    val tag = SystemFragment::class.java.simpleName
    var fragment = supportFragmentManager.findFragmentByTag(tag)
    if (fragment !is SystemFragment) {
      fragment = SystemFragment()
      supportFragmentManager.commit {
        add(fragmentContainerView.id, fragment, tag)
      }
    }
  }
}
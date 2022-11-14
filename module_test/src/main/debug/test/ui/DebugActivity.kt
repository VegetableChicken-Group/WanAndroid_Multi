package test.ui

import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.FragmentContainerView
import com.ndhzs.lib.base.BaseDebugActivity
import com.ndhzs.module.test.page.TestFragment
import kotlin.random.Random

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2022/5/28 0:23
 */
class DebugActivity : BaseDebugActivity() {

  override fun onDebugCreate(savedInstanceState: Bundle?) {
    val fcv = FragmentContainerView(this).apply {
      layoutParams = ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT
      )
      id = Random.nextInt() // 随机一个 id 即可
    }
    setContentView(fcv)
    replaceFragment(fcv.id) {
      TestFragment()
    }
  }
}
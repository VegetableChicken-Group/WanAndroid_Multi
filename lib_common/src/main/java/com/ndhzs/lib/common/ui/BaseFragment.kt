package com.ndhzs.lib.common.ui

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.ndhzs.lib.common.utils.BindView
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Suppress("UNCHECKED_CAST")
abstract class BaseFragment : Fragment() {
  
  /**
   * 在简单界面，使用这种方式来得到 View，避免使用 DataBinding 大材小用
   * ```
   * 使用方法：
   *    val mTvNum: TextView by R.id.xxx.view()
   *        .addInitialize {
   *           // 进行初始化的设置
   *        }
   *
   * 方便程度比较：
   *    kt 插件(被废弃) > 属性代理 > ButterKnife(被废弃) > DataBinding > ViewBinding
   *
   * 还有如果使用 DataBinding 和 ViewBinding 会因为 id 太长而劝退
   * ```
   */
  protected fun <T : View> Int.view() = BindView<T>(this) { this@BaseFragment }
  
  /**
   * 尤其注意这个 viewLifecycleOwner
   *
   * Fragment 与 View 的生命周期是不同的，而且一般情况下不会使用到 Fragment 的生命周期
   */
  protected fun <T> LiveData<T>.observe(observer: (T) -> Unit) {
    observe(viewLifecycleOwner, observer)
  }
  
  /**
   * 结合生命周期收集 Flow 方法
   */
  protected fun <T> Flow<T>.collectLaunch(action: suspend (value: T) -> Unit) {
    lifecycleScope.launch {
      flowWithLifecycle(viewLifecycleOwner.lifecycle).collect { action.invoke(it) }
    }
  }
}
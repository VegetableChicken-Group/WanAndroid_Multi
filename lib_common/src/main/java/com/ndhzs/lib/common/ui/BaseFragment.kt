package com.ndhzs.lib.common.ui

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.ndhzs.lib.common.utils.BindView
import java.lang.RuntimeException
import java.lang.reflect.ParameterizedType

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
}
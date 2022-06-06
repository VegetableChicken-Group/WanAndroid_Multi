package com.ndhzs.lib.common.ui

import android.view.View
import androidx.annotation.CallSuper
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.lifecycle.LiveData
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.ndhzs.lib.common.extensions.RxjavaLifecycle
import com.ndhzs.lib.common.utils.BindView
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Suppress("UNCHECKED_CAST")
abstract class BaseFragment : Fragment(), RxjavaLifecycle {
  
  @CallSuper
  override fun onDestroyView() {
    super.onDestroyView()
    // 取消 Rxjava 流
    mDisposableList.filter { !it.isDisposed }.forEach { it.dispose() }
    mDisposableList.clear()
  }
  
  /**
   * 在简单界面，使用这种方式来得到 View，避免使用 ViewBinding 大材小用
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
   * ViewBinding 是给所有布局都默认开启的，大项目严重拖垮编译速度
   * ```
   */
  protected fun <T : View> Int.view() = BindView<T>(this) { this@BaseFragment }
  
  /**
   * 替换 Fragment 的正确用法。
   * 如果不按照正确方式使用，会造成 ViewModel 失效，
   * 你可以写个 demo 看看在屏幕翻转后 Fragment 的 ViewModel 的 hashcode() 值是不是同一个
   */
  protected inline fun <reified F : Fragment> replaceFragment(
    @IdRes id: Int,
    fragmentManager: FragmentManager = childFragmentManager,
    func: () -> F
  ): F {
    var fragment = fragmentManager.findFragmentById(id)
    if (fragment !is F) {
      fragment = func.invoke()
      fragmentManager.commit {
        replace(id, fragment)
      }
    }
    return fragment
  }
  
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
  
  private val mDisposableList = mutableListOf<Disposable>()
  
  override fun onAddRxjava(disposable: Disposable) {
    mDisposableList.add(disposable)
  }
}
package com.ndhzs.lib.common.ui

import android.view.View
import androidx.annotation.CallSuper
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.ndhzs.lib.common.extensions.RxjavaLifecycle
import io.reactivex.rxjava3.disposables.Disposable

@Suppress("UNCHECKED_CAST")
abstract class BaseFragment : Fragment(), BaseUi, RxjavaLifecycle {
  
  @CallSuper
  override fun onDestroyView() {
    super.onDestroyView()
    // 取消 Rxjava 流
    mDisposableList.filter { !it.isDisposed }.forEach { it.dispose() }
    mDisposableList.clear()
  }
  
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
  
  private val mDisposableList = mutableListOf<Disposable>()
  
  /**
   * 实现 [RxjavaLifecycle] 的方法，用于带有生命周期的调用
   */
  override fun onAddRxjava(disposable: Disposable) {
    mDisposableList.add(disposable)
  }
  
  override val rootView: View
    get() = requireView()
}
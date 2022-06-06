package com.ndhzs.lib.common.ui

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.LiveData
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.ndhzs.lib.common.extensions.RxjavaLifecycle
import com.ndhzs.lib.common.utils.BindView
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 *@author 985892345
 *@email 2767465918@qq.com
 *@data 2021/5/25
 *@description
 */
abstract class BaseActivity(
  /**
   * 是否锁定竖屏
   */
  private val isPortraitScreen: Boolean = true,
  
  /**
   * 是否沉浸式状态栏
   *
   * 注意，沉浸式后，状态栏不会再有东西占位，界面会默认上移
   * 可以给根布局加上 android:fitsSystemWindows=true
   * 不同布局该属性效果不同，请给合适的布局添加
   */
  private val isCancelStatusBar: Boolean = true
) : AppCompatActivity(), RxjavaLifecycle {
  
  @CallSuper
  @SuppressLint("SourceLockedOrientationActivity")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    
    if (isPortraitScreen) { // 锁定竖屏
      requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }
    
    if (isCancelStatusBar) { // 沉浸式状态栏
      cancelStatusBar()
    }
  }
  
  @CallSuper
  override fun onDestroy() {
    super.onDestroy()
    // 取消 Rxjava 流
    mDisposableList.filter { !it.isDisposed }.forEach { it.dispose() }
    mDisposableList.clear()
  }
  
  private fun cancelStatusBar() {
    val window = this.window
    val decorView = window.decorView
    
    // 这是 Android 做了兼容的 Compat 包
    // 注意，使用了下面这个方法后，状态栏不会再有东西占位，
    // 可以给根布局加上 android:fitsSystemWindows=true
    // 不同布局该属性效果不同，请给合适的布局添加
    WindowCompat.setDecorFitsSystemWindows(window, false)
    val windowInsetsController = ViewCompat.getWindowInsetsController(decorView)
    windowInsetsController?.isAppearanceLightStatusBars = true // 设置状态栏字体颜色为黑色
    window.statusBarColor = Color.TRANSPARENT //把状态栏颜色设置成透明
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
  protected fun <T: View> Int.view() = BindView<T>(
    this,
    BindView.GetActivity { this@BaseActivity }
  )
  
  /**
   * 替换 Fragment 的正确用法。
   * 如果不按照正确方式使用，会造成 ViewModel 失效，
   * 你可以写个 demo 看看在屏幕翻转后 Fragment 的 ViewModel 的 hashcode() 值是不是同一个
   */
  protected inline fun <reified F : Fragment> replaceFragment(@IdRes id: Int, func: () -> F): F {
    var fragment = supportFragmentManager.findFragmentById(id)
    if (fragment !is F) {
      fragment = func.invoke()
      supportFragmentManager.commit {
        replace(id, fragment)
      }
    }
    return fragment
  }
  
  protected fun <T> LiveData<T>.observe(observer: (T) -> Unit) {
    observe(this@BaseActivity, observer)
  }
  
  /**
   * 结合生命周期收集 Flow 方法
   */
  protected fun <T> Flow<T>.collectLaunch(action: suspend (value: T) -> Unit) {
    lifecycleScope.launch {
      flowWithLifecycle(lifecycle).collect { action.invoke(it) }
    }
  }
  
  private val mDisposableList = mutableListOf<Disposable>()
  
  override fun onAddRxjava(disposable: Disposable) {
    mDisposableList.add(disposable)
  }
}

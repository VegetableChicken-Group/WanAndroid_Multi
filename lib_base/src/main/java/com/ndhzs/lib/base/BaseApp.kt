package com.ndhzs.lib.base

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.annotation.CallSuper
import com.alibaba.android.arouter.launcher.ARouter
import com.ndhzs.api.init.InitialManager
import com.ndhzs.lib.utils.utils.impl.ActivityLifecycleCallbacksImpl
import java.lang.ref.WeakReference
import java.util.*

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2022/5/26 14:01
 */
open class BaseApp : Application() {
  companion object {
    lateinit var baseApp: BaseApp
      private set
  
    /**
     * 获取栈顶的 Activity
     *
     * 栈顶 Activity 可用于实现全局 dialog
     */
    @SuppressLint("StaticFieldLeak")
    lateinit var topActivity: WeakReference<Activity>
      private set
  }
  
  private lateinit var mInitialManager: InitialManagerImpl
  
  @CallSuper
  override fun onCreate() {
    super.onCreate()
    baseApp = this
    initARouter()
    initInitialService()
    initActivityManger()
  }
  
  private fun initARouter() {
    if (BuildConfig.DEBUG) {
      ARouter.openLog()
      ARouter.openDebug()
    }
    ARouter.init(this)
  }
  
  private fun initInitialService() {
    mInitialManager = InitialManagerImpl(this)
  }
  
  private fun initActivityManger() {
    registerActivityLifecycleCallbacks(
      object : ActivityLifecycleCallbacksImpl {
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
          topActivity = WeakReference(activity)
        }
        
        override fun onActivityResumed(activity: Activity) {
          if (activity !== topActivity.get()) {
            topActivity = WeakReference(activity)
          }
        }
      }
    )
  }
  
  
  /**
   * BaseApp 不能直接实现于 InitialManager，不然会导致其他模块缺失 app_init 依赖
   */
  private class InitialManagerImpl(override val application: Application) : InitialManager {
    
    private val loader = ServiceLoader.load(com.ndhzs.api.init.InitialService::class.java)
  
    init {
      //由于android每开辟进程都会访问application的生命周期方法,所以为了保证sdk初始化无措，最好对其进行过滤。
      //因为有些sdk的初始化不是幂等的，即多次初始化会导致进程的crash。这样就会导致一些未知的问题。
      //所以解决方案就是对当前进程进程判断，只在main进程初始化sdk，其余进程默认不进行sdk的初始化。
      // (不排除某些sdk需要，比如友盟推送就需要在新开辟的:channel进行进行初始化)
      loader.forEach { it.onAllProcess(this) }
      if (isMainProcess()){
        onMainProcess()
      }else {
        onOtherProcess()
      }
    }
  
    //非主进程
    private fun onOtherProcess() {
      loader.forEach {
        it.onOtherProcess(this)
      }
    }
  
    //主进程
    private fun onMainProcess() {
      loader.forEach {
        it.onMainProcess(this)
      }
    }
  }
}

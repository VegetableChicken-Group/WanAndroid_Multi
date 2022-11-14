package com.ndhzs.lib.base

import android.app.Application
import androidx.annotation.CallSuper
import com.alibaba.android.arouter.launcher.ARouter
import com.ndhzs.api.init.InitialManager
import java.util.*

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2022/5/26 14:01
 */
open class BaseApp : Application(), InitialManager {
  companion object {
    lateinit var baseApp: BaseApp
      private set
  }
  
  @CallSuper
  override fun onCreate() {
    super.onCreate()
    baseApp = this
    initARouter()
    initInitialService()
  }
  
  private fun initARouter() {
    if (BuildConfig.DEBUG) {
      ARouter.openLog()
      ARouter.openDebug()
    }
    ARouter.init(this)
  }
  
  override val application: Application
    get() = this
  
  private val loader = ServiceLoader.load(com.ndhzs.api.init.InitialService::class.java)
  
  private fun initInitialService() {
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

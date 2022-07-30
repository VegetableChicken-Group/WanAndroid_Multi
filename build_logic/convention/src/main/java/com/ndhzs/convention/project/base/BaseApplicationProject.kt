@file:Suppress("UnstableApiUsage")

package com.ndhzs.convention.project.base

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.kotlin.dsl.apply
import com.ndhzs.convention.project.base.base.BaseAndroidProject
import com.ndhzs.convention.config.Config
import com.ndhzs.convention.depend.debugDependCodeLocator
import com.ndhzs.convention.depend.debugDependLeakCanary
import com.ndhzs.convention.depend.debugDependPandora
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2022/5/28 12:31
 */
abstract class BaseApplicationProject(project: Project) : BaseAndroidProject(project) {
  
  override fun initProjectInternal() {
    initApplication()
    super.initProjectInternal()
    debugDependLeakCanary() // 依赖 LeakCancry，检查内存泄漏
    debugDependPandora() // 依赖 Pandora，一个界面检查工具 https://github.com/whataa/pandora
    debugDependCodeLocator() // 字节在用的极其强大的调试工具：https://github.com/bytedance/CodeLocator
  }
  
  protected open fun initApplication() {
    apply(plugin = "com.android.application")
    apply(plugin = "kotlin-android")
    apply(plugin = "kotlin-kapt")
    
    extensions.configure<BaseAppModuleExtension> {
      initAndroid(this)
    }
  }
  
  // 配置 android 闭包
  protected open fun initAndroid(extension: BaseAppModuleExtension) {
    extension.run {
      uniformConfigAndroid()
      defaultConfig {
        applicationId = Config.getApplicationId(project)
        versionCode = Config.versionCode
        versionName = Config.versionName
        targetSdk = Config.targetSdk
      }
    }
  }
}
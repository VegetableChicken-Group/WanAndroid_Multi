@file:Suppress("UnstableApiUsage")

package project.base

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Action
import org.gradle.kotlin.dsl.apply
import project.base.base.BaseAndroidProject
import config.Config

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2022/5/28 12:31
 */
abstract class BaseApplicationProject : BaseAndroidProject() {
  
  override fun initProjectInternal() {
    initApplication()
    super.initProjectInternal()
  }
  
  protected open fun initApplication() {
    project.run {
      apply(plugin = "com.android.application")
      apply(plugin = "kotlin-android")
      apply(plugin = "kotlin-kapt")
  
      extensions.configure(
        "android",
        Action<BaseAppModuleExtension> {
          initAndroid(this)
        }
      )
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
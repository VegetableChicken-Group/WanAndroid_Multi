@file:Suppress("UnstableApiUsage")

package project.base

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Action
import org.gradle.api.Project
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
  
  override fun initProject(project: Project) {
    initApplication(project)
    super.initProject(project)
  }
  
  protected open fun initApplication(project: Project) {
    project.run {
      apply(plugin = "com.android.application")
      apply(plugin = "kotlin-android")
      apply(plugin = "kotlin-kapt")
  
      extensions.configure(
        "android",
        Action<BaseAppModuleExtension> {
          initAndroid(this, project)
        }
      )
    }
  }
  
  // 配置 android 闭包
  protected open fun initAndroid(extension: BaseAppModuleExtension, project: Project) {
    extension.run {
      uniformConfigAndroid(project)
      defaultConfig {
        applicationId = Config.getApplicationId(project)
        versionCode = Config.versionCode
        versionName = Config.versionName
        targetSdk = Config.targetSdk
      }
    }
  }
}
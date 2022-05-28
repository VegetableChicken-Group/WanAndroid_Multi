@file:Suppress("UnstableApiUsage")

package project.base

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import project.base.base.BaseAndroidProject
import versions.Config

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2022/5/28 12:52
 */
abstract class BaseLibraryProject : BaseAndroidProject() {
  
  override fun initProject(project: Project) {
    initLibrary(project)
    super.initProject(project)
  }
  
  protected open fun initLibrary(project: Project) {
    project.run {
      apply(plugin = "com.android.library")
      apply(plugin = "kotlin-android")
      apply(plugin = "kotlin-kapt")
  
      extensions.configure(
        "android",
        Action<LibraryExtension> {
          initAndroid(this, project)
        }
      )
    }
  }
  
  // 配置 android 闭包
  protected open fun initAndroid(extension: LibraryExtension, project: Project) {
    extension.run {
      uniformConfigAndroid(project)
      defaultConfig {
        targetSdk = Config.targetSdk
      }
    }
  }
}
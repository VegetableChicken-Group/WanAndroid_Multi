@file:Suppress("UnstableApiUsage")

package project.base

import com.android.build.gradle.LibraryExtension
import org.gradle.kotlin.dsl.apply
import project.base.base.BaseAndroidProject
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import utils.libsVersion

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2022/5/28 12:52
 */
abstract class BaseLibraryProject(project: Project) : BaseAndroidProject(project) {
  
  override fun initProjectInternal() {
    initLibrary()
    super.initProjectInternal()
  }
  
  protected open fun initLibrary() {
    apply(plugin = "com.android.library")
    apply(plugin = "kotlin-android")
    
    extensions.configure<LibraryExtension> {
      initAndroid(this)
    }
  }
  
  // 配置 android 闭包
  protected open fun initAndroid(extension: LibraryExtension) {
    extension.run {
      uniformConfigAndroid()
      defaultConfig {
        // 自己模块的混淆文件
        consumerProguardFiles.add(projectDir.resolve("consumer-rules.pro"))
      }
    
      buildFeatures {
        dataBinding = true
      }
    }
  }
}
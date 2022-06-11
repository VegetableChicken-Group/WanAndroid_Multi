@file:Suppress("UnstableApiUsage")

package com.ndhzs.build.logic.project.base

import com.android.build.gradle.LibraryExtension
import org.gradle.kotlin.dsl.apply
import com.ndhzs.build.logic.project.base.base.BaseAndroidProject
import com.ndhzs.build.logic.config.Config
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.plugin.KaptExtension

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
    apply(plugin = "kotlin-kapt")
    
    extensions.configure<LibraryExtension> {
      initAndroid(this)
    }
  
    extensions.configure<KaptExtension> {
      arguments {
        arg("AROUTER_MODULE_NAME", project.name)
        arg("room.schemaLocation", "${project.projectDir}/schemas") // room 的架构导出目录
      }
    }
  }
  
  // 配置 android 闭包
  protected open fun initAndroid(extension: LibraryExtension) {
    extension.run {
      uniformConfigAndroid()
      defaultConfig {
        targetSdk = Config.targetSdk
      }
    }
  }
}
@file:Suppress("UnstableApiUsage")

package com.ndhzs.build.logic.project.base

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
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
 * @date 2022/5/28 12:31
 */
abstract class BaseApplicationProject(project: Project) : BaseAndroidProject(project) {
  
  override fun initProjectInternal() {
    initApplication()
    super.initProjectInternal()
  }
  
  protected open fun initApplication() {
    apply(plugin = "com.android.application")
    apply(plugin = "kotlin-android")
    apply(plugin = "kotlin-kapt")
    
    extensions.configure<BaseAppModuleExtension> {
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
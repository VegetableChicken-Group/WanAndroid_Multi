@file:Suppress("UnstableApiUsage")

package project.base

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.g985892345.provider.plugin.gradle.extensions.KtProviderExtensions
import org.gradle.kotlin.dsl.apply
import project.base.base.BaseAndroidProject
import config.Config
import debugDependLibDebug
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import utils.libsVersion

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
    debugDependLibDebug() // 所有 application 模块默认在 debug 时依赖 lib_debug
  }
  
  protected open fun initApplication() {
    apply(plugin = "com.android.application")
    apply(plugin = "kotlin-android")
    
    extensions.configure<BaseAppModuleExtension> {
      initAndroid(this)
    }
    
    initKtProvider()
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
    
      buildFeatures {
        dataBinding = true
      }
    
      buildTypes {
        release {
          isShrinkResources = true
        }
      }
    }
  }
  
  private fun initKtProvider() {
    apply(plugin = "io.github.985892345.KtProvider")
    extensions.configure(KtProviderExtensions::class) {
      packageName {
        include("com.ndhzs.module")
        include("com.ndhzs.lib")
      }
    }
  }
}
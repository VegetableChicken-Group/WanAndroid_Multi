@file:Suppress("UnstableApiUsage")

package project

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import lib.dependLibCommon
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project
import project.base.BaseApplicationProject

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2022/5/28 12:23
 */
object ModuleDebugProject : BaseApplicationProject() {
  
  override fun initProjectInternal() {
    checkIsInDebug()
    super.initProjectInternal()
  }
  
  override fun initProject() {
    dependLibCommon()
  }
  
  override fun initAndroid(extension: BaseAppModuleExtension) {
    super.initAndroid(extension)
    extension.run {
      // 设置 debug 的源集
      sourceSets {
        getByName("main") {
          /*
          * 重定向 AndroidManifest 文件和 java 代码
          * 以后统一将 debug 用到的 java 代码和 AndroidManifest 文件放在 main/debug 下
          * */
          manifest.srcFile("src/main/debug/AndroidManifest.xml")
          java {
            srcDir("src/main/debug")
          }
        }
      }
    }
  }
  
  /**
   * 检查该模块是否处于 debug 状态
   */
  private fun Project.checkIsInDebug() {
    if (!name.startsWith("module_") && name != "module_app") {
      throw RuntimeException("该插件只能给 module 使用！")
    }
  
    if (plugins.hasPlugin("com.android.library")) {
      throw RuntimeException("开启单模块调试前，请先注释多模块插件！")
    }
  }
}
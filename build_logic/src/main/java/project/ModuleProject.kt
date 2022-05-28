package project

import org.gradle.api.Project
import project.base.BaseLibraryProject

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2022/5/28 12:22
 */
object ModuleProject : BaseLibraryProject() {
  
  override fun Project.init() {
    checkIsInDebug()
  }
  
  /**
   * 检查该模块是否处于 debug 状态
   */
  private fun Project.checkIsInDebug() {
    if (plugins.hasPlugin("com.android.application")) {
      throw RuntimeException("取消单模块调试才能使用多模块插件！")
    }
  }
}
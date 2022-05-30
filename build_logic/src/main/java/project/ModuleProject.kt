package project

import lib.dependLibCommon
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project
import project.base.BaseLibraryProject

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2022/5/28 12:22
 */
object ModuleProject : BaseLibraryProject() {
  
  override fun initProject() {
    checkIsInDebug()
    dependLibCommon()
  }
  
  /**
   * 检查该模块是否处于 debug 状态
   */
  private fun checkIsInDebug() {
    if (plugins.hasPlugin("com.android.application")) {
      throw RuntimeException("取消单模块调试才能使用多模块插件！")
    }
  }
}
package project.base.base

import org.gradle.api.*

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2022/5/28 12:19
 */
@Suppress("UsePropertyAccessSyntax")
abstract class BaseProject(project: Project) : Project by project {
  
  fun apply() {
    initProjectInternal()
  }
  
  /**
   * 只有 [initProject] 的时候，存在忘记写 super 的情况
   */
  protected open fun initProjectInternal() {
    initProject()
  }
  
  protected abstract fun initProject()
}
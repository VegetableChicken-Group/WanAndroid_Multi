package com.ndhzs.convention.project.base.base

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
   * 增加这个方法是为了强制实现类调用 super，
   * 把这个与 [initProject] 也是可以的，但存在忘记写 super 的情况
   *
   * 也可以认为
   */
  protected open fun initProjectInternal() {
    initProject()
  }
  
  protected abstract fun initProject()
}
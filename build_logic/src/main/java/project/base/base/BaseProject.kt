package project.base.base

import org.gradle.api.Project

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2022/5/28 12:19
 */
abstract class BaseProject {
  open fun initProject(project: Project) {
    project.init()
  }
  abstract fun Project.init()
}
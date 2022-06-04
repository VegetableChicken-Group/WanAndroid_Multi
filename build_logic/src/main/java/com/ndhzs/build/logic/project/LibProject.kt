package com.ndhzs.build.logic.project

import com.ndhzs.build.logic.depend.lib.dependLibCommon
import com.ndhzs.build.logic.project.base.BaseLibraryProject
import org.gradle.api.Project

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2022/5/28 12:21
 */
class LibProject(project: Project) : BaseLibraryProject(project) {
  override fun initProject() {
    dependLibCommon()
  }
  
  override fun isDependChildModule(): Boolean {
//    if (name == "lib_account") {
//      println(name)
//      return false
//    }
    return super.isDependChildModule()
  }
}
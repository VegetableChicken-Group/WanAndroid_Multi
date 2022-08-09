package com.ndhzs.convention.project

import com.ndhzs.convention.depend.dependAndroidKtx
import com.ndhzs.convention.depend.dependAndroidView
import com.ndhzs.convention.depend.dependLifecycleKtx
import com.ndhzs.convention.depend.dependNetworkInternal
import com.ndhzs.convention.project.base.BaseLibraryProject
import org.gradle.api.Project

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email guo985892345@foxmail.com
 * @date 2022/8/1 13:07
 */
class LibUtilsProject(project: Project) : BaseLibraryProject(project) {
  override fun initProject() {
    // 这里面只依赖带有 internal 修饰的
    dependAndroidView()
    dependAndroidKtx()
    dependLifecycleKtx()
    dependNetworkInternal()
  }
}
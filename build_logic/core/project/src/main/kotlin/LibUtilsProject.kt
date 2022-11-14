package project

import dependAndroidKtx
import dependAndroidView
import dependLifecycleKtx
import dependNetworkInternal
import project.base.BaseLibraryProject
import org.gradle.api.Project

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email guo985892345@foxmail.com
 * @date 2022/8/1 13:07
 */
class LibUtilsProject(project: Project) : BaseLibraryProject(project) {
  override fun initProject() {
    dependAndroidView()
    dependAndroidKtx()
    dependLifecycleKtx()
    dependNetworkInternal()
  }
}
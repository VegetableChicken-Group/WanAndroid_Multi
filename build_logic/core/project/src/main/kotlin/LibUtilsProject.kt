package project

import dependAndroidKtx
import dependAndroidView
import dependLifecycleKtx
import dependNetworkInternal
import project.base.BaseLibraryProject
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import utils.libsVersion

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
    // 对 KtProviderManager 进行单独封装
    dependencies {
      val krProviderVersion = libsVersion("ktProvider")
      // 如果你只是 Kotlin/Jvm 项目（比如 Android 项目），只需要依赖 -jvm 即可
      "implementation"("io.github.985892345:provider-manager-jvm:$krProviderVersion")
    }
  }
}
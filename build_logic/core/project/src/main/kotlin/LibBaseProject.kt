package project

import dependAndroidKtx
import dependAndroidView
import dependLifecycleKtx
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
class LibBaseProject(project: Project) : BaseLibraryProject(project) {
  override fun initProject() {
    // 这里面只依赖带有 internal 修饰的
    dependAndroidView()
    dependAndroidKtx()
    dependLifecycleKtx()
    
    // 在 base 模块中的 BaseApp 中初始化 KtProvider
    // 但是因为 KtProvider 的 gradle 插件只能放在启动模块，所以启动模块必须继承 BaseApp 实现自己的 Application
    dependencies {
      val krProviderVersion = libsVersion("ktProvider")
      // 如果你只是 Kotlin/Jvm 项目（比如 Android 项目），只需要依赖 -jvm 即可
      "api"("io.github.985892345:provider-init-jvm:$krProviderVersion")
    }
  }
}
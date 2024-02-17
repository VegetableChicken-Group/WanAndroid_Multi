@file:Suppress("UnstableApiUsage")

package project

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import dependAndroidKtx
import dependAndroidView
import dependLifecycleKtx
import org.gradle.api.Project
import project.base.BaseApplicationProject
import org.gradle.kotlin.dsl.dependencies

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2022/5/28 12:23
 */
class ModuleDebugProject(project: Project) : BaseApplicationProject(project) {
  
  override fun initProjectInternal() {
    checkIsInDebug()
    super.initProjectInternal()
  }
  
  override fun initProject() {
    dependAndroidView()
    dependAndroidKtx()
    dependLifecycleKtx()
  }
  
  override fun initAndroid(extension: BaseAppModuleExtension) {
    super.initAndroid(extension)
    extension.run {
      // 设置 debug 的源集
      sourceSets {
        getByName("main") {
          // 将 debug 加入编译环境，单模块需要的代码放这里面
          java.srcDir("src/main/debug")
          res.srcDir("src/main/debug-res")
          // 如果 debug 下存在 AndroidManifest 文件，则重定向 AndroidManifest 文件
          if (projectDir.resolve("src")
              .resolve("main")
              .resolve("debug")
              .resolve("AndroidManifest.xml").exists()) {
            manifest.srcFile("src/main/debug/AndroidManifest.xml")
          }
        }
      }
      defaultConfig {
        // 设置单模块安装包名字
        manifestPlaceholders["single_module_app_name"] = project.name
      }
      // 依赖 lib_single 用于设置单模块入口
      dependencies {
        "implementation"(project(":lib_single"))
      }
    }
  }
  
  /**
   * 检查该模块是否处于 debug 状态
   */
  private fun Project.checkIsInDebug() {
    if (plugins.hasPlugin("com.android.library")) {
      throw RuntimeException("开启单模块调试前，请先注释多模块插件！")
    }
  }
}
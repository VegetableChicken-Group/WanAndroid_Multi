import project.ModuleDebugProject
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import project.LibSingleProject

/**
 * 单模块调试有两个重要点:
 * - 如何区分当前模块是否开启了单模块调试
 * - 单模块打包时对于 api 模块的实现模块的依赖反转
 *
 * 1. 对于如何区分当前模块是否开启了单模块调试:
 * - 通过 gradle task name 来判断，详细可看 [isAllowDebugModule]
 *
 * 2. 对于单模块打包时对于 api 模块的实现模块的依赖反转:
 * - 在单模块时我们一般只依赖了 api 模块，而实现模块必须需要加入编译环境才能正常打包
 * - 所以需要再打包时进行依赖反转，去依赖实现模块
 * - 该功能的实现在 [LibSingleProject]
 *
 * @author 985892345 (Guo Xiangrui)
 * @email guo985892345@foxmail.com
 * @date 2022/8/12 12:59
 */
class ModuleDebugPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    target.run {
      if (isAllowDebugModule()) {
        doDebugModule()
      } else {
        cancelDebugModule()
      }
    }
  }
  
  // 是否允许执行单模块调试
  private fun Project.isAllowDebugModule(): Boolean {
    return !project.gradle.startParameter.taskNames.any {
      // 注意：这里面的是取反，即满足下面条件的不执行单模块调试
      it.contains("assembleRelease")
        || it.contains("assembleDebug") && !it.contains(project.name)
        || it == "publishModuleCachePublicationToMavenRepository" // 本地缓存任务
        || it == "cacheToLocalMaven"
        || it == "channelRelease"
        || it == "channelDebug"
    }
  }
  
  // 允许执行单模块调试
  private fun Project.doDebugModule() {
    ModuleDebugProject(project).apply()
    apply(plugin = "plugin-cache")
  }
  
  // 不允许执行单模块调试
  private fun Project.cancelDebugModule() {
    println("${project.name} 的单模块调试被取消！")
    apply(plugin = "module-manager")
  }
}
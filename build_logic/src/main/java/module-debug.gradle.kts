import com.ndhzs.build.logic.project.ModuleDebugProject

// 是否允许执行单模块调试
fun isAllowDebugModule(): Boolean {
  return gradle.startParameter.taskNames.any { it == "assembleRelease" }
}

isAllowDebugModule().run {
  if (this) cancelDebugModule() else doDebugModule()
}

// 允许执行单模块调试
fun doDebugModule() {
  ModuleDebugProject(project).apply()
  plugins {
    id("com.ndhzs.build.logic.publish.publications")
  }
}

// 不允许执行单模块调试
fun cancelDebugModule() {
  println("${project.name} 模块的单模块调试被取消！")
  apply(plugin = "module-manager")
}


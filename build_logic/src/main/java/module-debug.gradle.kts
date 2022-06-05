import com.ndhzs.build.logic.project.ModuleDebugProject

// 不允许执行单模块调试
fun isNotAllowDebugModule(): Boolean {
  return gradle.startParameter.taskNames.any {
    it == "assembleRelease" // gradle 直接打包
      || it == "assembleDebug"
      || it == ":module_app:assembleRelease"
      || it == ":module_app:assembleDebug"
      || it == "publishModuleCachePublicationToMavenRepository" // 本地缓存任务
      || it == "cacheToLocalMaven"
  }
}

isNotAllowDebugModule().run {
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
  println("${project.name} 的单模块调试被取消！")
  apply(plugin = "module-manager")
}


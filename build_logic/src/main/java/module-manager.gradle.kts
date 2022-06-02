import com.ndhzs.build.logic.project.*

/**
 * 不同模块分配不同的插件
 */
val projectName: String = project.name
when {
  projectName == "module_app" -> AppProject.initProject(project)
  projectName == "lib_common" -> LibCommonProject.initProject(project)
  projectName.startsWith("module_") -> ModuleProject.initProject(project)
  projectName.startsWith("lib_") -> LibProject.initProject(project)
  projectName.startsWith("api_") -> ApiProject.initProject(project)
  else -> throw Exception("出现未知类型模块: name = $projectName   dir = $projectDir\n请为该模块声明对应的依赖插件！")
}


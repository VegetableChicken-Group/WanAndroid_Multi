@file:Suppress("PackageDirectoryMismatch")

import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

/**
 * 为了统一模块依赖，所以写了这个类
 *
 * 注意: 该类不建议有包名，因为不写包名可以不用导包
 *
 * @author 985892345
 * @date 2022/11/9 17:29
 */
object LibDepend {
  /*
  * 注意事项：
  * 1、别忘了前面要打引号
  * 2、建议按顺序添加
  * 3、一般情况下只有共用的才会添加，比如像 lib_account 这种，只需要添加它的 api 模块就够了，
  *   没必要添加它的 lib 模块，因为没有其他模块会使用
  *
  * 写了后会由一个 gradle 脚本自动生成对应 dependLib*() 方法
  * */
  
  const val account = ":lib_account"
  const val base = ":lib_base"
  const val config = ":lib_config"
  const val crash = ":lib_crash"
  const val debug = ":lib_debug"
  const val utils = ":lib_utils"
}

////////////////////////////////////////////////////////////////////////////////////////////////////
//
//     如果你的模块需要单独写依赖逻辑，请以 fun Project.xxx[Name]() 开头书写，这样脚本就不会自动生成对应方法
//
////////////////////////////////////////////////////////////////////////////////////////////////////

/**
 * 依赖 lib_debug 模块
 *
 * 这个模块里面单独放只在 debug 下使用的依赖
 */
fun Project.debugDependLibDebug() {
  if (!gradle.startParameter.taskNames.any { it.contains("Release") }) {
//    apply(plugin = "pandora-plugin")
  }
  dependencies {
    "debugImplementation"(project(LibDepend.debug))
  }
}
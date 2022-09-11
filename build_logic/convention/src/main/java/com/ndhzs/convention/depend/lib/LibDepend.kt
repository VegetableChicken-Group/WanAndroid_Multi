package com.ndhzs.convention.depend.lib

import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2022/5/30 11:24
 */
object LibDepend {
  /*
  * 注意事项：
  * 1、别忘了前面要打引号
  * 2、建议按顺序添加
  * 3、一般情况下只有共用的才会添加，比如像 lib_account 这种，只需要添加它的 api 模块就够了，
  *   没必要添加它的 lib 模块，因为没有其他模块会使用
  * */

  const val base = ":lib_base"
  const val config = ":lib_config"
  const val debug = ":lib_debug"
  const val utils = ":lib_utils"
}

fun Project.dependLibBase() {
  dependencies {
    "implementation"(project(LibDepend.base))
  }
}

fun Project.dependLibConfig() {
  dependencies {
    "implementation"(project(LibDepend.config))
  }
}

fun Project.dependLibUtils() {
  dependencies {
    "implementation"(project(LibDepend.utils))
  }
}

/**
 * 依赖 lib_debug 模块
 *
 * 这个模块里面单独放只在 debug 下使用的依赖
 */
internal fun Project.debugDependLibDebug() {
  if (!gradle.startParameter.taskNames.any { it.contains("Release") }) {
    apply(plugin = "pandora-plugin")
  }
  dependencies {
    "debugImplementation"(project(LibDepend.debug))
  }
}
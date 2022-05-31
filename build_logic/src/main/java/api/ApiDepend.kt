package api

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2022/5/30 17:52
 */

object ApiDepend {
  const val account = ":lib_account:api_account"
  const val main = ":module_main:api_main"
  const val test = ":module_test:api_test"
  const val web = ":lib_web:api_web"
}

/**
 * 对于单模块调试，需要反向依赖实现模块，不然 ARouter 无法找到，会报空指针
 *
 * 该方法默认提供子模块的父模块依赖，因为一般父模块都是子模块的实现模块
 *
 * @param path 路径，比如：:lib_account:api_account
 * @param extraImplDepend 依赖除父模块外的额外实现模块，由你自己提供
 */
fun Project.dependApi(path: String, extraImplDepend: (() -> List<String>)? = null) {
  dependencies {
    "implementation"(project(path))
    if (plugins.hasPlugin("com.android.application")) {
      // 有这个插件说明是单模块调试，因为 module_app 模块不会引入依赖
      extraImplDepend?.invoke()?.forEach {
        "implementation"(project(it))
      }
      val parentProjectPath = path.substringBeforeLast(":")
      if (parentProjectPath.isNotEmpty()) {
        "implementation"(project(parentProjectPath))
      }
    }
  }
}


fun Project.dependApiAccount(extraImplDepend: (() -> List<String>)? = null) {
  dependApi(ApiDepend.account, extraImplDepend)
}

fun Project.dependApiMain(extraImplDepend: (() -> List<String>)? = null) {
  dependApi(ApiDepend.main, extraImplDepend)
}

fun Project.dependApiTest(extraImplDepend: (() -> List<String>)? = null) {
  dependApi(ApiDepend.test, extraImplDepend)
}

fun Project.dependApiWeb(extraImplDepend: (() -> List<String>)? = null) {
  dependApi(ApiDepend.web, extraImplDepend)
}
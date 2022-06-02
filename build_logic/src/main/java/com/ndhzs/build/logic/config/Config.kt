@file:Suppress("ObjectPropertyName")

package com.ndhzs.build.logic.config

import org.gradle.api.Project

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2022/5/26 15:13
 */
object Config {
  const val minSdk = 21
  const val targetSdk = 31
  const val compileSdk = 31
  
  const val versionCode = 1
  const val versionName = "0.1.0"
  
  fun getApplicationId(project: Project): String {
    return when (project.name) {
      "module_app" -> "com.ndhzs.wanandroid"
      else -> "com.ndhzs.wanandroid.${project.name}"
    }
  }
}

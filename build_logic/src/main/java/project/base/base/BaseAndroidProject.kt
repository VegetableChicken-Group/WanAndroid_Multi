@file:Suppress("UnstableApiUsage")

package project.base.base

import com.android.build.api.dsl.*
import lib.dependARouter
import lib.dependTestBase
import org.gradle.api.Action
import org.gradle.api.JavaVersion
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import org.jetbrains.kotlin.gradle.plugin.KaptExtension
import config.Config
import lib.Compose
import lib.dependAndroidBase

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2022/5/28 12:34
 */
abstract class BaseAndroidProject : BaseProject() {
  
  override fun initProjectInternal() {
    dependencies {
      if (isDependChildModule()) {
        // 自动依赖自己目录下的子模块
        dependChildModule()
      }
    }
    // 本来可以不依赖 Test，但每次那个 test 文件夹都报错，有时候又忘了删，强迫症
    dependTestBase()
    // 所有 Android 工程模块都需要
    dependAndroidBase()
    // 所有 Android 工程模块都需要依赖 ARouter
    dependARouter()
    super.initProjectInternal()
  }
  
  open fun isDependChildModule(): Boolean = true
  
  /**
   * 统一配置 android 闭包的公共部分
   *
   * 除了那个 targetSdk 以外，因为他没有写在顶层接口
   */
  protected fun <A : BuildFeatures, B : BuildType, C : DefaultConfig, D : ProductFlavor>
    CommonExtension<A, B, C, D>.uniformConfigAndroid() {
    
    compileSdk = Config.compileSdk
    defaultConfig {
      minSdk = Config.minSdk
    }
    buildTypes {
      release {
        isMinifyEnabled = true
        proguardFiles(
          getDefaultProguardFile("proguard-android-optimize.txt"),
          "proguard-rules.pro"
        )
      }
      debug {
        isMinifyEnabled = false
        proguardFiles(
          getDefaultProguardFile("proguard-android-optimize.txt"),
          "proguard-rules.pro"
        )
      }
    }
    
    compileOptions {
      sourceCompatibility = JavaVersion.VERSION_1_8
      targetCompatibility = JavaVersion.VERSION_1_8
    }
    
    lint {
      // 编译遇到错误不退出
      abortOnError = false
      // 错误输出文件
      baseline = project.file("lint-baseline.xml")
    }
    
    buildFeatures {
      viewBinding = true
      compose = true
    }

    composeOptions {
      kotlinCompilerExtensionVersion = Compose.compose_version
    }
    
    (this as ExtensionAware).extensions.configure(
      "kotlinOptions",
      Action<KotlinJvmOptions> {
        jvmTarget = "1.8"
      }
    )
  }
  
  /**
   * 自动依赖自己目录下的子模块
   */
  private fun DependencyHandlerScope.dependChildModule() {
    
    // 根 gradle 中包含的所有子模块
    val includeProjects = rootProject.subprojects.map { it.name }
    
    projectDir.listFiles()!!.filter {
      // 1.是文件夹
      // 2.以 lib_ 或者 api_ 开头
      // 3.根 gradle 导入了的模块
      it.isDirectory
        && "(lib_.+)|(api_.+)".toRegex().matches(it.name)
        && includeProjects.contains(it.name)
    }.forEach {
      "implementation"(project(":${name}:${it.name}"))
    }
  }
}
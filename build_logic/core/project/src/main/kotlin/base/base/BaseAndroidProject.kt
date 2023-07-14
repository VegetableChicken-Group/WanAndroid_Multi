@file:Suppress("UnstableApiUsage")

package project.base.base

import check.AndroidProjectChecker
import check.rule.ModuleNamespaceCheckRule
import com.android.build.api.dsl.*
import org.gradle.api.JavaVersion
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import config.Config
import dependAndroidBase
import dependTestBase
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import utils.libsVersion

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2022/5/28 12:34
 */
abstract class BaseAndroidProject(project: Project) : BaseProject(project) {
  
  override fun initProjectInternal() {
    // 项目检查工具
    AndroidProjectChecker.configBefore(project)
    
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
    // 所有 Android 工程模块都需要依赖 KtProvider
    dependencies {
      val krProviderVersion = libsVersion("ktProvider")
      // 如果你只是 Kotlin/Jvm 项目（比如 Android 项目），只需要依赖 -jvm 即可
      "implementation"("io.github.985892345:provider-annotation-jvm:$krProviderVersion")
    }
    super.initProjectInternal()
  
    // 项目检查工具
    AndroidProjectChecker.configAfter(project)
  }
  
  /**
   * 是否自动依赖自己目录下的子模块
   */
  open fun isDependChildModule(): Boolean = true
  
  /**
   * 统一配置 android 闭包的公共部分
   *
   * 除了那个 targetSdk 以外，因为他没有写在顶层接口
   */
  protected fun <A : BuildFeatures, B : BuildType, C : DefaultConfig, D : ProductFlavor>
    CommonExtension<A, B, C, D>.uniformConfigAndroid() {
    
    namespace = ModuleNamespaceCheckRule.getCorrectNamespace(project)
    compileSdk = Config.compileSdk
    defaultConfig {
      minSdk = Config.minSdk
      testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
      release {
        isMinifyEnabled = true
        proguardFiles(
          getDefaultProguardFile("proguard-android-optimize.txt"),
          rootDir.resolve("build_logic")
            .resolve("core")
            .resolve("project")
            .resolve("proguard-rules.pro")
        )
      }
      debug {
        isMinifyEnabled = false
        proguardFiles(
          getDefaultProguardFile("proguard-android-optimize.txt"),
          rootDir.resolve("build_logic")
            .resolve("core")
            .resolve("project")
            .resolve("proguard-rules.pro")
        )
      }
    }
    
    compileOptions {
      sourceCompatibility = JavaVersion.VERSION_17
      targetCompatibility = JavaVersion.VERSION_17
    }
    
    lint {
      // 编译遇到错误不退出
      abortOnError = false
    }
    
    (this as ExtensionAware).extensions.configure<KotlinJvmOptions> {
      jvmTarget = "17"
    }
  
    // 命名规范设置，因为多模块相同资源名在打包时会合并，所以必须强制开启
    resourcePrefix = project.name.substringAfter("_")
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
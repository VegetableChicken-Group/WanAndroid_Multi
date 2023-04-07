// [account, base, config, crash, utils]
// 自动生成的代码，请不要修改 !!!
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

fun Project.dependLibAccount() {
  dependencies {
    "implementation"(project(LibDepend.account))
  }
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

fun Project.dependLibCrash() {
  dependencies {
    "implementation"(project(LibDepend.crash))
  }
}

fun Project.dependLibUtils() {
  dependencies {
    "implementation"(project(LibDepend.utils))
  }
}
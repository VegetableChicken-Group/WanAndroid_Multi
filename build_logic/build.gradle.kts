plugins {
  `kotlin-dsl`
}

dependencies {
  implementation("com.android.tools.build:gradle:7.2.1")
  implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.0")
}

// 插件的定义
gradlePlugin {
  plugins {
//    create("module-debug") {
//      id = "module-debug"
//      implementationClass = "project.ModuleDebugProject"
//    }
  }
}

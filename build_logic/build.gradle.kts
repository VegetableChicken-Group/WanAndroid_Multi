plugins {
  `kotlin-dsl`
}

repositories {
  mavenCentral()
  google()
}

dependencies {
  implementation("com.android.tools.build:gradle:7.1.3")
  implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
}

// 插件的定义
gradlePlugin {
  plugins {
    create("module-debug") {
      id = "module-debug"
      implementationClass = "project.ModuleDebugProject"
    }
  }
}

plugins {
  `kotlin-dsl`
}

repositories {
  maven { url = uri("https://maven.aliyun.com/repository/public") }
  maven { url = uri("https://maven.aliyun.com/repository/google") }
  mavenCentral()
  google()
}

dependencies {
  implementation("com.android.tools.build:gradle:7.2.1")
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

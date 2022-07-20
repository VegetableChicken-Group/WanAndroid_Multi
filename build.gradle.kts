// 管理 git 提交规范的脚本
apply(from = "git-hook.gradle.kts")

tasks.register<Delete>("clean") {
  delete(rootProject.buildDir)
}

tasks.register("cacheToLocalMaven") {
  group = "publishing"
  subprojects
    .map { it.tasks.named("cacheToLocalMaven") }
    .let { dependsOn(it) }
}

buildscript {
  repositories {
    maven { url = uri("https://maven.aliyun.com/repository/public") }
    maven { url = uri("https://maven.aliyun.com/repository/google") }
    mavenCentral()
    google()
  }
  dependencies {
    classpath(libs.android.gradlePlugin)
    classpath(libs.kotlin.gradlePlugin)
    classpath(libs.hilt.gradlePlugin)
  }
}
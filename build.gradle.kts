
/**
 * 其实下面这个本来可以不需要的，但每次新建 Activity，都会自动加一个 classpath，
 * 导致编译失败，所以默认就加起吧，每次都去删那个 classpath 很麻烦
 * 注意：该版本号需要与 build_login 中的 build.gradle.kts 同步修改，因为是项目的 build.gradle，所以无法做到统一
 */
plugins {
  id("com.android.application") version "7.2.1" apply false
  id("com.android.library") version "7.2.1" apply false
  id("org.jetbrains.kotlin.android") version "1.6.21" apply false
}

tasks.register<Delete>("clean") {
  delete(rootProject.buildDir)
}

tasks.register("cacheToLocalMaven") {
  group = "publishing"
  subprojects
    .map { it.tasks.named("cacheToLocalMaven") }
    .let { dependsOn(it) }
}
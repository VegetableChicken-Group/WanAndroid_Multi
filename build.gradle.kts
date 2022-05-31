
/**
 * 其实下面这个本来可以不需要的，但每次新建 Activity，都会自动加一个 classpath，
 * 所以就加起吧，每次都去删很麻烦
 * 注意：该版本号需要与 build_login 中的 build.gradle.kts 同步修改
 */
plugins {
  id("com.android.application") version "7.2.1" apply false
  id("com.android.library") version "7.2.1" apply false
  id("org.jetbrains.kotlin.android") version "1.6.21" apply false
}

tasks.register<Delete>("clean") {
  delete(rootProject.buildDir)
}
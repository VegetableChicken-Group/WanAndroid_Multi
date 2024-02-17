// 管理 git 提交规范的脚本
apply(from = "git-hook.gradle.kts")

tasks.register<Delete>("clean") {
  delete(rootProject.buildDir)
}

tasks.register("cacheToLocalMaven") {
  group = "publishing"
  subprojects
    .mapNotNull { it.tasks.findByName("cacheToLocalMaven") }
    .let { dependsOn(it) }
}

buildscript {
  repositories {
    google()
    mavenCentral() // 优先 MavenCentral，一是：github CI 下不了 aliyun 依赖；二是：开 VPN 访问 aliyun 反而变慢了
    maven("https://jitpack.io")
    jcenter()
    maven { url = uri("https://maven.aliyun.com/repository/public") }
    maven { url = uri("https://maven.aliyun.com/repository/google") }
  }
  dependencies {
    /*
    * 可能你会好奇这里与 build-logic 中有什么区别，
    * 如果你在 build-logic 中要使用插件，需要 implementation() 才行，
    * 而如果你只是在某个模块里面使用，那直接在这里写即可，但请写好注释和对应链接！！！
    * */
    classpath(libs.android.gradlePlugin)
    classpath(libs.kotlin.gradlePlugin)
    classpath(libs.ksp.gradlePlugin)
    
    /*
    * 一个轻量级 Android AOP 框架，在本项目中 CodeLocator 需要使用
    * CodeLocator：字节在用的强大的调试工具，请查看：https://github.com/bytedance/CodeLocator
    * 由于原 LanceX 项目没有适配新版本的 gradle，在 issues 中找到了适配的版本：https://github.com/liujianAndroid/lancet
    * 但又因为某些无法解决的问题，使用后编译失败，所以暂时注释
    * */
    //        classpath("com.bytedance.tools.lancet:lancet-plugin-asm6:1.0.2")
    
    /**
     * 每次新建模块这里斗湖自己加一个：
     * classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:x.x.xx")
     * TODO 把它删掉！！！！！
     */
  }
}
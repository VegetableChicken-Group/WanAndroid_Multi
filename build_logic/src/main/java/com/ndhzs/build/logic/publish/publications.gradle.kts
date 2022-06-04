package com.ndhzs.build.logic.publish

import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension

plugins {
  `maven-publish`
}

if (plugins.hasPlugin("com.android.application")) {
  extensions.configure(
    "android",
    Action<BaseAppModuleExtension> {
      publishing {
        singleVariant("debug")
      }
    }
  )
} else if (plugins.hasPlugin("com.android.library")) {
  extensions.configure(
    "android",
    Action<LibraryExtension> {
      publishing {
        singleVariant("debug")
      }
    }
  )
} else {
  throw RuntimeException("只允许给 application 和 library 进行缓存，如有其他模块，请额外实现逻辑！")
}

afterEvaluate {
  publishing {
    publications {
      create<MavenPublication>("moduleCache") {
        from(components["debug"])
      }

      // https://docs.gradle.org/current/userguide/publishing_maven.html#header
      repositories {
        maven {
          url = uri("$rootDir/maven")
          group = "com.ndhzs.wanandroid"
          name = name
          version = "cache"
        }
      }
    }
  }
}

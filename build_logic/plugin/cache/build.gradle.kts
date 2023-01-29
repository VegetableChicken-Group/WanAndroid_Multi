plugins {
  `kotlin-dsl`
}

dependencies {
  implementation(libs.android.gradlePlugin)
  implementation(libs.kotlin.gradlePlugin)
}

gradlePlugin {
  plugins {
    create("plugin-cache"){
      implementationClass = "CachePlugin"
      id = "plugin-cache"
    }
  }
}
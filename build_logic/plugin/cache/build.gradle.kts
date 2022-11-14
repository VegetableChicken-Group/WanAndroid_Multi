plugins {
  `kotlin-dsl`
}

java {
  sourceCompatibility = JavaVersion.VERSION_1_8
  targetCompatibility = JavaVersion.VERSION_1_8
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
plugins {
  `kotlin-dsl`
}

java {
  sourceCompatibility = JavaVersion.VERSION_1_8
  targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
  api(project(":core:project"))
  api(project(":plugin:cache"))
}

gradlePlugin {
  plugins {
    create("module-debug") {
      id = "module-debug"
      implementationClass = "ModuleDebugPlugin"
    }
    
    create("module-manager") {
      id = "module-manager"
      implementationClass = "ModuleManagerPlugin"
    }
  }
}
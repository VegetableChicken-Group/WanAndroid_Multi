plugins {
  `kotlin-dsl`
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
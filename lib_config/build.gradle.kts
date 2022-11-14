plugins {
  id("module-manager")
}

dependAutoService()

dependencies {
  implementation(project(":api_init"))
}

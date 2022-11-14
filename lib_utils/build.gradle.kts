plugins {
  id("module-manager")
}

dependLibConfig()

dependCoroutines()
dependCoroutinesRx3()
dependGlide()
dependNetwork()
dependRxjava()
dependRxPermissions()

dependAutoService()

dependencies {
  implementation(project(":api_init"))
}
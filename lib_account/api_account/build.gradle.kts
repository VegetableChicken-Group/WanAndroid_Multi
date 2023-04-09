plugins {
  id("module-manager")
}

dependRxjava()

dependencies {
  implementation(Network.okhttp)
}
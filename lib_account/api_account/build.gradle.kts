import lib.dependRxjava

plugins {
  id("module-manager")
}

dependRxjava()

dependencies {
  implementation(lib.Network.okhttp)
}
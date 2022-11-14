plugins {
  id("module-manager")
}

dependLibUtils()

dependRxjava()

dependencies {
  implementation(Network.okhttp)
}
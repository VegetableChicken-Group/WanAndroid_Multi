import lib.dependCoroutines
import lib.dependRxjava

plugins {
  id("module-manager")
}

dependRxjava()
dependCoroutines()

dependencies {
  implementation(lib.Network.okhttp)
  implementation(lib.Lifecycle.`lifecycle-reactivestreams-ktx`)
  implementation(lib.Lifecycle.`livedata-ktx`)
}
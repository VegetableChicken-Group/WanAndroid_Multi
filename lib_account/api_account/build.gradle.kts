import lib.dependCoroutines
import lib.dependCoroutinesRx3
import lib.dependRxjava

plugins {
  id("module-manager")
}

dependRxjava()
dependCoroutines()
dependCoroutinesRx3()

dependencies {
  implementation(lib.Network.okhttp)
  implementation(lib.Lifecycle.`lifecycle-reactivestreams-ktx`)
  implementation(lib.Lifecycle.`livedata-ktx`)
}
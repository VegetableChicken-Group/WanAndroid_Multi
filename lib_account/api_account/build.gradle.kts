import com.ndhzs.build.logic.depend.*

plugins {
  id("module-manager")
}

dependRxjava()
dependCoroutines()
dependCoroutinesRx3()

dependencies {
  implementation(Network.okhttp)
  implementation(Lifecycle.`lifecycle-reactivestreams-ktx`)
  implementation(Lifecycle.`livedata-ktx`)
}
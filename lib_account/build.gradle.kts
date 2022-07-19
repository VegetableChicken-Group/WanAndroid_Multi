import com.ndhzs.convention.depend.*

plugins {
  id("module-manager")
}

dependRxjava()
dependNetwork()

dependencies {
  implementation("com.github.franmontiel:PersistentCookieJar:v1.0.1")
}
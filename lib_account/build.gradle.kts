import com.ndhzs.build.logic.depend.*

plugins {
  id("module-manager")
}

dependRxjava()
dependNetwork()

dependencies {
  implementation("com.github.franmontiel:PersistentCookieJar:v1.0.1")
}
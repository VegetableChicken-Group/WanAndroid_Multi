import com.ndhzs.convention.depend.api.*
import com.ndhzs.convention.depend.*

plugins {
  id("module-manager")
}

dependRxjava()
dependGlide()

dependApiAccount()

dependencies {
  implementation(Network.okhttp)
}
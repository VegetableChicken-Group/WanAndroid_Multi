import com.ndhzs.convention.depend.*
import com.ndhzs.convention.depend.lib.dependLibUtils

plugins {
  id("module-manager")
}

dependLibUtils()

dependRxjava()

dependencies {
  implementation(Network.okhttp)
}
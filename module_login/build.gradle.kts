import com.ndhzs.convention.depend.api.dependApiAccount
import com.ndhzs.convention.depend.*

plugins {
  id("module-manager")
//  id("module-debug")
}

dependNetwork()
dependRxjava()
dependCoroutines()
dependCoroutinesRx3()

dependApiAccount()

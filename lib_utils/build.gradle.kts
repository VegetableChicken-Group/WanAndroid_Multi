import com.ndhzs.convention.depend.*
import com.ndhzs.convention.depend.api.dependApiAccount
import com.ndhzs.convention.depend.lib.dependLibConfig

plugins {
  id("module-manager")
}

dependLibConfig()

dependApiAccount()

dependCoroutines()
dependCoroutinesRx3()
dependGlide()
dependNetwork()
dependRxjava()
dependRxPermissions()
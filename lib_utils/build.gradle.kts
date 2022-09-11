import com.ndhzs.convention.depend.*
import com.ndhzs.convention.depend.lib.dependLibConfig

plugins {
  id("module-manager")
}

dependLibConfig()

dependCoroutines()
dependCoroutinesRx3()
dependGlide()
dependNetwork()
dependRxjava()
dependRxPermissions()
import com.ndhzs.convention.depend.api.dependApiAccount
import com.ndhzs.convention.depend.*
import com.ndhzs.convention.depend.lib.dependLibBase
import com.ndhzs.convention.depend.lib.dependLibConfig
import com.ndhzs.convention.depend.lib.dependLibUtils

plugins {
  id("module-debug")
}

dependLibBase()
dependLibUtils()
dependLibConfig()

dependNetwork()
dependRxjava()
dependCoroutines()
dependCoroutinesRx3()

dependApiAccount()

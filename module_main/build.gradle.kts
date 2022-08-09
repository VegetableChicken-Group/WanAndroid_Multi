import com.ndhzs.convention.depend.api.dependApiTest
import com.ndhzs.convention.depend.lib.dependLibBase
import com.ndhzs.convention.depend.lib.dependLibConfig
import com.ndhzs.convention.depend.lib.dependLibUtils

plugins {
  id("module-manager")
}

dependLibBase()
dependLibUtils()
dependLibConfig()

dependApiTest()

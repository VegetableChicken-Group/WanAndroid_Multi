import com.ndhzs.convention.depend.Network
import com.ndhzs.convention.depend.api.dependApiAccount
import com.ndhzs.convention.depend.dependCoroutines
import com.ndhzs.convention.depend.dependCoroutinesRx3
import com.ndhzs.convention.depend.lib.dependLibConfig
import com.ndhzs.convention.depend.lib.dependLibUtils

plugins {
  id("module-manager")
}

dependLibUtils()
dependLibConfig()

dependApiAccount()

dependCoroutines()
dependCoroutinesRx3()

dependencies {
  implementation(Network.okhttp) // 为了拿到 CookieJar
}
import com.ndhzs.build.logic.depend.api.dependApiAccount
import com.ndhzs.build.logic.depend.api.dependApiTest
import com.ndhzs.build.logic.depend.dependAndroidKtx
import com.ndhzs.build.logic.depend.dependAndroidView
import com.ndhzs.build.logic.depend.dependGlide
import com.ndhzs.build.logic.depend.dependRxjava

plugins {
  id("module-manager")
}

dependAndroidView()
dependAndroidKtx()
dependRxjava()
dependGlide()

dependApiAccount()
dependApiTest()

dependencies {
  implementation(project(":module_test:api_test"))
  implementation(com.ndhzs.build.logic.depend.Network.okhttp)
}
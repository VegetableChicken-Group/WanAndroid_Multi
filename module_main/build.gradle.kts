import lib.dependCoroutines
import api.dependApiTest
import lib.dependAndroidKtx
import lib.dependAndroidView

plugins {
  id("module-manager")
}

dependAndroidView()
dependAndroidKtx()
dependCoroutines()
dependApiTest()

dependencies {
  implementation(project(":module_test:api_test"))
}
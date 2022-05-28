import lib.dependAndroidKtx
import lib.dependAndroidView

plugins {
  id("module-manager")
}

dependAndroidView()
dependAndroidKtx()

dependencies {
  implementation(project(":module_test:api_test"))
}
import api.dependApiAccount
import lib.*

plugins {
//  id("module-manager")
  id("module-debug")
}

dependAndroidView()
dependAndroidKtx()
dependLifecycleKtx()

dependNetwork()
dependRxjava()
dependCoroutines()
dependCoroutinesRx3()

dependApiAccount()

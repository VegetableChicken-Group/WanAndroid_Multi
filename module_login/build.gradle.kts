import lib.*

plugins {
//  id("module-manager")
  id("module-debug")
}

dependAndroidView()
dependAndroidKtx()
dependNetwork()
dependRxjava()
dependCoroutines()
dependCoroutinesRx3()
dependLibAccount()

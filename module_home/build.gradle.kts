import lib.*

plugins {
    id("module-manager")
    // id("module-debug")
}

dependAndroidView()
dependAndroidKtx()
dependNetwork()
dependCoroutines()
dependPaging()
dependGlide()
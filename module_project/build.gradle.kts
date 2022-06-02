import lib.*

plugins {
//    id("module-manager")
    id("module-debug")
}

android {
    buildFeatures {
        dataBinding = true
    }
}

dependAndroidView()
dependAndroidKtx()
dependGlide()
dependNetwork()
dependPaging()
dependRoom()
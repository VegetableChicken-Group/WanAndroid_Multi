import lib.*

plugins {
    id("module-manager")
//  id("module-debug")
}

dependCompose()
dependAndroidKtx()
dependLifecycleKtx()
dependNetwork()
dependRxjava()
dependPaging()
dependencies {
    implementation("androidx.paging:paging-compose:1.0.0-alpha14")
    implementation("io.coil-kt:coil-compose:1.4.0")
    implementation("io.coil-kt:coil:1.4.0")
    // 这里面写该只有自己模块才会用到的依赖
}
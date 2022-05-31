import lib.dependAndroidKtx
import lib.dependAndroidView

plugins {
    id("module-manager")
//  id("module-debug")
}

dependAndroidView()
dependAndroidKtx()

dependencies {
// 这里面写该只有自己模块才会用到的依赖
}
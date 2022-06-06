
import com.ndhzs.build.logic.depend.dependAndroidKtx
import com.ndhzs.build.logic.depend.dependAndroidView

plugins {
    id("module-manager")
//  id("module-debug")
}

dependAndroidView()
dependAndroidKtx()

dependencies {
// 这里面写该只有自己模块才会用到的依赖
}
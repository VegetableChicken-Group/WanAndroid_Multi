import com.ndhzs.build.logic.depend.api.dependApiMain
import com.ndhzs.build.logic.depend.dependAndroidKtx
import com.ndhzs.build.logic.depend.dependAndroidView
import com.ndhzs.build.logic.depend.dependLifecycleKtx

plugins {
    id("module-manager")
//  id("module-debug")
}

dependAndroidView()
dependAndroidKtx()

dependencies {
// 这里面写该只有自己模块才会用到的依赖
}
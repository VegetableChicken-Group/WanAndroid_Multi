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
dependLifecycleKtx()
// 还有其他比如: 协程、Retrofit、Glide 等，可以去 build_logic 中寻找
// 基本上能用到的全都有依赖方式

// 依赖 api 模块
dependApiMain()

dependencies {
  // 这里面写只有自己模块才会用到的额外依赖
  // 如果 build_logic 中已有，请直接使用
  // 可以通过 Ctrl + F 搜索项目关键词快速查看是否存在相同依赖
}
import com.ndhzs.build.logic.depend.*
import com.ndhzs.build.logic.depend.api.dependApiWeb

plugins {
    id("module-debug")
    id("org.jetbrains.kotlin.android")
}

// 如果该模块要使用网络请求，就调用该方法
dependNetwork()
// 如果该模块要使用协程
dependCoroutines()
// 主流模块都已经设置了方法来依赖，输入: dependXXX() 查看更多依赖
dependRxjava()
dependAndroidView()
dependAndroidKtx()
dependLifecycleKtx()
dependApiWeb()
dependPaging()

dependencies {
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    // 这里面写只有自己模块才会用到的依赖
}
import lib.dependCoroutines
import lib.dependNetwork

plugins {
    //id("module-manager")
    id("module-debug")
}

android {
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

// 如果该模块要使用网络请求，就调用该方法
dependNetwork()
// 如果该模块要使用协程
dependCoroutines()
// 主流模块都已经设置了方法来依赖，输入: dependXXX() 查看更多依赖

dependencies {
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("androidx.recyclerview:recyclerview:1.2.0-alpha01")
    // 这里面写只有自己模块才会用到的依赖
}
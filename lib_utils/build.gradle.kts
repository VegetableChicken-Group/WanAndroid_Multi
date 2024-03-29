plugins {
  id("module-manager")
}

dependLibConfig()

dependApiInit()
dependApiAccount()

dependCoroutines()
dependCoroutinesRx3()
dependGlide()
dependNetwork()
dependRxjava()
dependRxPermissions()



android {
  buildFeatures {
    buildConfig = true
  }
  defaultConfig {
    // 写入版本信息到 BuildConfig，其他模块可以通过调用 getAppVersionCode() 和 getAppVersionName() 方法获得
    buildConfigField("long", "VERSION_CODE", config.Config.versionCode.toString())
    buildConfigField("String", "VERSION_NAME", "\"${config.Config.versionName}\"")
  }
}
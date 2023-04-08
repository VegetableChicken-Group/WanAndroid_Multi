plugins {
  id("module-manager")
}

dependLibConfig()

dependApiAccount()

dependCoroutines()
dependCoroutinesRx3()
dependGlide()
dependNetwork()
dependRxjava()
dependRxPermissions()
dependAutoService()



dependencies {
  implementation(project(":api_init")) // 因为 api_init 没有实现模块，所以写这里
}

android {
  defaultConfig {
    // 写入版本信息到 BuildConfig，其他模块可以通过调用 getAppVersionCode() 和 getAppVersionName() 方法获得
    buildConfigField("long", "VERSION_CODE", config.Config.versionCode.toString())
    buildConfigField("String", "VERSION_NAME", "\"${config.Config.versionName}\"")
  }
}
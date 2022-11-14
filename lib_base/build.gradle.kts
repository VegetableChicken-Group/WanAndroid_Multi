plugins {
  id("module-manager")
}

dependLibUtils()
dependLibConfig()

dependApiAccount()

dependCoroutines()
dependRxjava()

dependencies {
  implementation(Network.okhttp) // 为了拿到 CookieJar
  api(project(":api_init"))
}
plugins {
  id("module-manager")
}

dependLibUtils()
dependLibConfig()

dependApiInit()
dependApiAccount()

dependCoroutines()
dependRxjava()

dependencies {
  implementation(Network.okhttp) // 为了拿到 CookieJar
}
plugins {
  id("module-manager")
}

dependLibBase()
dependLibUtils()
dependLibConfig()

dependRxjava()
dependNetwork()

dependencies {
  // 一个保存 Cookie 的第三方库
  implementation("com.github.franmontiel:PersistentCookieJar:v1.0.1")
}
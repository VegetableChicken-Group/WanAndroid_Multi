import com.ndhzs.convention.depend.*

plugins {
  id("module-manager")
}

dependRxjava()
dependNetwork()

dependencies {
  // 一个保存 Cookie 的第三方库
  implementation("com.github.franmontiel:PersistentCookieJar:v1.0.1")
}
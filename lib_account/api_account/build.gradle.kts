plugins {
  id("module-manager")
}

// 这依赖了 utils 模块后，utils 模块不能再依赖 api_account，
// 但我在 utils 中单独写了接口用于获取 api_account 的东西
dependLibUtils()

dependRxjava()

dependencies {
  implementation(Network.okhttp)
}